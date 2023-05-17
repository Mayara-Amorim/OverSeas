package br.com.fiap.overseas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.overseas.model.EnderecoModel;
@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {

}
