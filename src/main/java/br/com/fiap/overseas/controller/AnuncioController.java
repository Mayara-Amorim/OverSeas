package br.com.fiap.overseas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.overseas.model.AnuncioModel;
import br.com.fiap.overseas.repository.AnuncioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/anuncios")
@CrossOrigin(origins="*", allowedHeaders="*")
public class AnuncioController {
	@Autowired
	private AnuncioRepository aR;
	
	@GetMapping
	public ResponseEntity <List<AnuncioModel>> getAll(){
		return ResponseEntity.ok(aR.findAll());
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<AnuncioModel> getById(@PathVariable Long id){
		return aR.findById(id)
		.map(resposta -> ResponseEntity.ok(resposta))
		.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity <List<AnuncioModel>> getByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(aR.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<AnuncioModel> post(@Valid @RequestBody AnuncioModel aM){
		return ResponseEntity.status(HttpStatus.CREATED).body(aR.save(aM));
	}
	
	
	@PutMapping
	public ResponseEntity<AnuncioModel>put(@Valid @RequestBody AnuncioModel aM){
		return aR.findById(aM.getId())
				.map(resp-> ResponseEntity.status(HttpStatus.OK)
				.body(aR.save(aM)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
	Optional<AnuncioModel> anuncio =	aR.findById(id);
		if(anuncio.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		aR.deleteById(id);
	}
	
	
	
	
	
}
