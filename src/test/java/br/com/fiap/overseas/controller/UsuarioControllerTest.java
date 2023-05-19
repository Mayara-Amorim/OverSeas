package br.com.fiap.overseas.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.fiap.overseas.model.UsuarioModel;
import br.com.fiap.overseas.repository.UsuarioRepository;
import br.com.fiap.overseas.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
	@Autowired
	private TestRestTemplate testRestTemplate;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@BeforeAll
	void star() {
		usuarioRepository.deleteAll();
		usuarioService.cadastrarUsuario(new UsuarioModel(0L,"root","12345678910","root@root.com", "11976009844 ", "root"," ", null));
	}
	@Test
	@DisplayName("Cria um Usuario")
	public void deveCriarUmUsuario() {
		HttpEntity<UsuarioModel>corpoRequisicao = new HttpEntity<UsuarioModel>(new UsuarioModel(0L, "Paulo Antunes", "12345678910","paulo_antunes@gmail.com", "1220304050", "rootroot", null, null));
		
		ResponseEntity<UsuarioModel>corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, UsuarioModel.class);
		assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
		assertEquals(corpoRequisicao.getBody().getEmail(), corpoResposta.getBody().getEmail());
		
	}
	@Test
	@DisplayName("Testando se a aplicação permite usuarios duplicados")
	public void naoDeveDuplicarUsuario() {
		usuarioService.cadastrarUsuario(new UsuarioModel(0L,"Maria da Silva", "12345678910","maria@gmail.com", "123456789", "rootroot", null, null));
		
		HttpEntity<UsuarioModel> corpoRequisicao = new HttpEntity<UsuarioModel> (new UsuarioModel(0L,"Maria da Silva", "12345678910", "maria@gmail.com", "123456789", "rootroot",null, null));
		ResponseEntity<UsuarioModel> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, UsuarioModel.class);
		assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
	
	}
	@Test
	@DisplayName("Atualizar um Usuario")
	public void atualizarUmUsuario() {
		Optional<UsuarioModel> usuarioCadastrado = usuarioService
				.cadastrarUsuario(new UsuarioModel(0L, "Juliana Andrews",  "12345678910","juliana_andrews@gmail.com", "123456789", "root", null, null));
		
		UsuarioModel usuarioUpdate = new UsuarioModel(usuarioCadastrado.get()
				.getId(), "Juliana Andrews Ramos",  "12345678910","juliana_ramos@gmail.com", "123456789", "rootroot", null, null);
		HttpEntity<UsuarioModel> corpoRequicao = new HttpEntity<UsuarioModel>(usuarioUpdate);
		ResponseEntity<UsuarioModel>corpoResposta = testRestTemplate
				.withBasicAuth("root@root.com", "root")
				.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequicao, UsuarioModel.class);
		
		
		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		assertEquals(corpoRequicao.getBody().getNome(), corpoResposta.getBody().getNome());
		assertEquals(corpoRequicao.getBody().getEmail(), corpoResposta.getBody().getEmail());
	}
	@Test
	@DisplayName("Listando todos os Usuários")
	public void deveMostarTodos() {
	usuarioService.cadastrarUsuario(new UsuarioModel(0L, "Sabrina Sanchez",  "12345678910","sabrina_sanchez@gmail.com", "12345678910", "rootroot", null, null));
	usuarioService.cadastrarUsuario(new UsuarioModel(0L, "Ricardo Santos", "12345678910", "ricardo_santos@gmail.com", "12345678910", "rootroot", null, null));
	ResponseEntity<String> resposta = testRestTemplate
			.withBasicAuth("root@root.com", "root")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);
	assertEquals(HttpStatus.OK, resposta.getStatusCode());
	
	}
}
	