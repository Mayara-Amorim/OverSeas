package br.com.fiap.overseas.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
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
	
	@Schema(example = "email@email.com.br")
	@Email(message = "O Atributo email deve ser um email válido!")
	@jakarta.validation.constraints.NotBlank
	@Size(min=5, message="O valor minimo é de 5 caracteres. Preste atenção")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private EnderecoModel endereco;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=1, max=15, message="O valor minimo é de 9 e o valor maximo é de 15. Preste atenção")
	private String telefone;
	

	@NotNull
	@Size(min=5, message="O valor minimo é de 5 caracteres. Preste atenção")
	private String senha;
	
	private String foto;
	
	@UpdateTimestamp
	private LocalDateTime dataCadastro;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<ReservaModel> reserva;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<AnuncioModel> anuncio;
	
	
	public UsuarioModel(){
		
	}

	public UsuarioModel(Long id, String nome, String cpf, String email, String telefone,
		String senha, String foto, LocalDateTime dataCadastro) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.foto = foto;
		this.dataCadastro = dataCadastro;

	}

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

	public List<ReservaModel> getReserva() {
		return reserva;
	}

	public void setReserva(List<ReservaModel> reserva) {
		this.reserva = reserva;
	}
	public EnderecoModel getEndereco() {
		return endereco;
	}
	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}
	
	public List<AnuncioModel> getAnuncio() {
		return anuncio;
	}
	public void setAnuncio(List<AnuncioModel> anuncio) {
		this.anuncio = anuncio;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDateTime dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	
	
	
	

}
