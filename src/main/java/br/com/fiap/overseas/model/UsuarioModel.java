package br.com.fiap.overseas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_usuarios")
public class UsuarioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@jakarta.validation.constraints.NotBlank
	@Size(min=3, max=60, message="O valor minimo é de 3 e o valor maximo é de 60. Preste atenção")
	private String nome;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=10, max=12, message="O valor minimo é de 10 e o valor maximo é de 12. Preste atenção")
	private String cpf;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=5, message="O valor minimo é de 5 caracteres. Preste atenção")
	private String email;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=1, max=15, message="O valor minimo é de 9 e o valor maximo é de 15. Preste atenção")
	private String telefone;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=5, message="O valor minimo é de 5 caracteres. Preste atenção")
	private String senha;
	
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	

}
