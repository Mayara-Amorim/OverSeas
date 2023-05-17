package br.com.fiap.overseas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.overseas.model.UsuarioModel;

public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

}
