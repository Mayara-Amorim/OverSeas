package br.com.fiap.overseas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.overseas.model.AnuncioModel;
@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioModel, Long>{

}
