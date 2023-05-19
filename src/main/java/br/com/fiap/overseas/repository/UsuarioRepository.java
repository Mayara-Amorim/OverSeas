package br.com.fiap.overseas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.overseas.model.UsuarioModel;
@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {
	public Optional<UsuarioModel> findByEmail(String email);
	public List <UsuarioModel> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

}
