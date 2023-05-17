package br.com.fiap.overseas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import br.com.fiap.overseas.model.EnderecoModel;
import br.com.fiap.overseas.repository.EnderecoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/enderecos")
@CrossOrigin(origins="*", allowedHeaders = "*")
public class EnderecoController {
	@Autowired
	private EnderecoRepository eR;

	
	
	@GetMapping
	public ResponseEntity<List<EnderecoModel>> getAll(){
		return ResponseEntity.ok(eR.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EnderecoModel> getById(@PathVariable Long id){
		return eR.findById(id).map(resp-> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/estado/{estado}")
	public ResponseEntity<List<EnderecoModel>> getEstado(@PathVariable String estado){
		return ResponseEntity.ok(eR.findAllByEstadoContainingIgnoreCase(estado));
	}
	
		@PostMapping
		public ResponseEntity<EnderecoModel> post(@Valid @RequestBody EnderecoModel eM){
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(eR.save(eM));			
		}
		
	
	//O certo era não deixar cadastrar um endereço sem usuario, mas eu teria de mudar a modelagem
	@PutMapping
	public ResponseEntity<EnderecoModel> put(@Valid @RequestBody EnderecoModel eM){
		return eR.findById(eM.getId()).map(resp-> ResponseEntity.status(HttpStatus.OK)
				.body(eR.save(eM))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());			
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<EnderecoModel> endereco = eR.findById(id);
		
		if(endereco.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		eR.deleteById(id);
	}

}
