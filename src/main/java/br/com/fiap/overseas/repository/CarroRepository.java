package br.com.fiap.overseas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.overseas.model.CarroModel;
@Repository
public interface CarroRepository extends JpaRepository<CarroModel, Long> {
	
	 public List<CarroModel> findAllByModeloContainingIgnoreCase(@Param("modelo") String modelo);

}
