package br.com.fiap.overseas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.overseas.model.CarroModel;
import br.com.fiap.overseas.model.EnderecoModel;

public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

}
