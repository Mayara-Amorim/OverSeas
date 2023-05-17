package br.com.fiap.overseas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.overseas.model.ReservaModel;
@Repository
public interface ReservaRepository extends JpaRepository<ReservaModel, Long>{

}
