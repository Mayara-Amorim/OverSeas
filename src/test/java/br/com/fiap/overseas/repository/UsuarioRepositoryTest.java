package br.com.fiap.overseas.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.fiap.overseas.model.UsuarioModel;
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	@Autowired
	private UsuarioRepository ur;
	@BeforeAll
	void start() {
		ur.deleteAll();
		ur.save(new UsuarioModel(0L,"João da Silva",  "12345678910","joão@gmail.com", "9999900009 ", "admin1234", "", null));
		ur.save(new UsuarioModel(0L,"Maria Cristina Silva",  "22375678910","maria@gmail.com", "1199900009 ", "admin1234", "", null));
		ur.save(new UsuarioModel(0L,"Gustavo Strada",  "12345678910","gustv@gmail.com", "1395900009 ", "admin1234", "", null));
		ur.save(new UsuarioModel(0L,"Vitoria Albuquerque Silva Santos",  "12345678910","vivi@gmail.com", "9195670000 ", "admin1234", "", null));
		
		
	}
	@Test
	@DisplayName("Retorna 1 usuario")
	public void deveRetornarUmUsuario() {
		Optional<UsuarioModel> usuario =ur.findByEmail("joão@gmail.com");
		assertTrue(usuario.get().getEmail().equals("joão@gmail.com"));
	}
	@Test
	@DisplayName("retorna 3 usuarios")
	public void deveRetornarTresUsuarios() {
		List<UsuarioModel> listaUsuario = ur.findAllByNomeContainingIgnoreCase("Silva");
		assertEquals(3, listaUsuario.size());
		assertTrue(listaUsuario.get(0).getNome().equals("João da Silva"));
		assertTrue(listaUsuario.get(1).getNome().equals("Maria Cristina Silva"));
		assertTrue(listaUsuario.get(2).getNome().equals("Vitoria Albuquerque Silva Santos"));
	}

	@AfterAll
	public void end() {
		ur.deleteAll();
		
	}
}
