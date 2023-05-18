package br.com.fiap.overseas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.overseas.model.UsuarioModel;
import br.com.fiap.overseas.repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	@Autowired	
	private UsuarioRepository uR;
	
	   @GetMapping("/all")
	    public ResponseEntity <List<UsuarioModel>> getAll(){     
	        return ResponseEntity.ok(uR.findAll());  
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<UsuarioModel> getById(@PathVariable Long id) {
	        return uR.findById(id)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.notFound().build());
	    }
	    

}