package br.com.fiap.overseas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.overseas.model.AnuncioModel;
@Repository
public interface AnuncioRepository extends JpaRepository<AnuncioModel, Long>{
	public List<AnuncioModel> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
