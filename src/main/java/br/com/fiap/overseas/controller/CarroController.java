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

import br.com.fiap.overseas.model.CarroModel;
import br.com.fiap.overseas.repository.CarroRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carros")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CarroController {
	@Autowired
	private CarroRepository cR;
	
	  @GetMapping
	    public ResponseEntity<List<CarroModel>> getAll(){
	        return ResponseEntity.ok(cR.findAll());
	    }

	@GetMapping("/{id}")
	public ResponseEntity<CarroModel> getById(@PathVariable Long id) {
		return cR.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());

	}

	@GetMapping("/modelo/{modelo}")
	public ResponseEntity<List<CarroModel>> getByModel(@PathVariable String modelo) {
		return ResponseEntity.ok(cR.findAllByModeloContainingIgnoreCase(modelo));
	}

	@PostMapping
	public ResponseEntity<CarroModel> post(@Valid @RequestBody CarroModel carro) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cR.save(carro));
	}

	@PutMapping
	public ResponseEntity<CarroModel> put(@Valid @RequestBody CarroModel carro) {
		return cR.findById(carro.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(cR.save(carro)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<CarroModel> carro = cR.findById(id);

		if (carro.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		cR.deleteById(id);
	}

}
