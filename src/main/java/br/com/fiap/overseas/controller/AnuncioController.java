package br.com.fiap.overseas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.overseas.model.AnuncioModel;
import br.com.fiap.overseas.repository.AnuncioRepository;

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

}
