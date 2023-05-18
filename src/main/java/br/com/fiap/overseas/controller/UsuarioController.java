package br.com.fiap.overseas.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.overseas.model.UsuarioLoginDTO;
import br.com.fiap.overseas.model.UsuarioModel;
import br.com.fiap.overseas.repository.UsuarioRepository;
import br.com.fiap.overseas.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UsuarioController {
	@Autowired	
	private UsuarioRepository uR;
	@Autowired
	private UsuarioService uS;
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
	    
	    @PostMapping("/logar")
	    public ResponseEntity<UsuarioLoginDTO> login(@RequestBody Optional<UsuarioLoginDTO> usuarioLogin) {
	        return uS.autenticarUsuario(usuarioLogin)
	            .map(resposta -> ResponseEntity.ok(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	    }

	    @PostMapping("/cadastrar")
	    public ResponseEntity<UsuarioModel> postUsuario(@Valid @RequestBody UsuarioModel usuario) {

	        return  uS.cadastrarUsuario(usuario)
	            .map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

	    }

	    @PutMapping("/atualizar")
	    public ResponseEntity<UsuarioModel> putUsuario(@Valid @RequestBody UsuarioModel usuario) {
	        return  uS.atualizarUsuario(usuario)
	            .map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
	            .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	    }

	}
	    
	    
