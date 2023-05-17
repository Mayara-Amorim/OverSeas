package br.com.fiap.overseas.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_anuncios")
public class AnuncioModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="O atributo titulo é obrigatório")
	@Size(min=5, max=45, message="O titulo deve conter no minimo 5 caracteres e no maximo 45 caracteres")
	private String titulo;
	
	@NotBlank(message="O atributo titulo é obrigatório")
	@Size(min=5, max=255, message="O titulo deve conter no minimo 5 caracteres e no maximo 255 caracteres")
	private String descricao;
	
	@UpdateTimestamp
	private LocalDateTime data;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "carro_id", referencedColumnName = "id")
    private CarroModel carro;
	
	@ManyToOne
	@JsonIgnoreProperties("anuncio")
	private UsuarioModel usuario;
	
	

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public CarroModel getCarro() {
		return carro;
	}

	public void setCarro(CarroModel carro) {
		this.carro = carro;
	}
	
	
	
	

}
