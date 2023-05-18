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

import br.com.fiap.overseas.model.ReservaModel;
import br.com.fiap.overseas.repository.CarroRepository;
import br.com.fiap.overseas.repository.ReservaRepository;
import br.com.fiap.overseas.repository.UsuarioRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/reservas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ReservaController {
	
	@Autowired
	private ReservaRepository rR;
	@Autowired
	private CarroRepository cR;
	@Autowired
	private UsuarioRepository uR;
	
	@GetMapping
	public ResponseEntity <List<ReservaModel>> getAll(){
		return ResponseEntity.ok(rR.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <ReservaModel> getById(@PathVariable Long id){
		return rR.findById(id)
				.map(resp-> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@PostMapping
	public ResponseEntity<ReservaModel> post(@Valid @RequestBody ReservaModel rM){
		if(cR.existsById(rM.getCarro().getId()) && uR.existsById(rM.getUsuario().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(rR.save(rM));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@PutMapping
	public ResponseEntity<ReservaModel> put(@Valid @RequestBody ReservaModel rM){
		if(rR.existsById(rM.getId())) {
			if(cR.existsById(rM.getCarro().getId()) && uR.existsById(rM.getUsuario().getId()))
				return ResponseEntity.status(HttpStatus.OK)
						.body(rR.save(rM));
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();	
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		Optional<ReservaModel> reserva = rR.findById(id);
		if(reserva.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		rR.deleteById(id);
		
	}
	
	
	
}
