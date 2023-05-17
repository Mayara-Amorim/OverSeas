package br.com.fiap.overseas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.overseas.model.EnderecoModel;
@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoModel, Long> {
	
	public List<EnderecoModel>findAllByEstadoContainingIgnoreCase(@Param("estado")String estado);

}
