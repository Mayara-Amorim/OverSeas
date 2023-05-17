package br.com.fiap.overseas.model;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="tb_carros")
public class CarroModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@jakarta.validation.constraints.NotBlank
	@Size(min=2, max=12, message="O valor minimo é de 2 e o valor maximo é de 12. Preste atenção")
	private String marca;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=2, max=20, message="O valor minimo é de 2 e o valor maximo é de 20. Preste atenção")
	private String modelo;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=2, max=10, message="O valor minimo é de 2 e o valor maximo é de 10. Preste atenção")
	private String placa;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=2, max=4, message="O valor minimo é de 2 e o valor maximo é de 4. Preste atenção")
	private String ano;
	

	@jakarta.validation.constraints.NotBlank
	@Size(min=6, max=10, message="O valor minimo é de 2 e o valor maximo é de 12. Preste atenção")
	private String tipo;
	
	private String foto;
	
	@jakarta.validation.constraints.NotBlank
	private double valorHora;
	
	@jakarta.validation.constraints.NotBlank
	private double velocidade;
	
	@jakarta.validation.constraints.NotBlank
	private double autonomiaKm;
	
	private String cor;
	
	@UpdateTimestamp
	private LocalDateTime dataCadastroCarro;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
	private EnderecoModel endereco;
	
	@OneToMany(mappedBy = "carro", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("carro")
	private List<ReservaModel> reserva;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}
	public String getAno() {
		return ano;
	}
	public void setAno(String ano) {
		this.ano = ano;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public double getValorHora() {
		return valorHora;
	}
	public void setValorHora(double valorHora) {
		this.valorHora = valorHora;
	}
	public double getVelocidade() {
		return velocidade;
	}
	public void setVelocidadde(double velocidade) {
		this.velocidade = velocidade;
	}
	public double getAutonomiaKm() {
		return autonomiaKm;
	}
	public void setAutonomiaKm(double autonomiaKm) {
		this.autonomiaKm = autonomiaKm;
	}
	public String getCor() {
		return cor;
	}
	public EnderecoModel getEndereco() {
		return endereco;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public void setEndereco(EnderecoModel endereco) {
		this.endereco = endereco;
	}
	public List<ReservaModel> getReserva() {
		return reserva;
	}
	public void setReserva(List<ReservaModel> reserva) {
		this.reserva = reserva;
	}
	public LocalDateTime getDataCadastroCarro() {
		return dataCadastroCarro;
	}
	public void setDataCadastroCarro(LocalDateTime dataCadastroCarro) {
		this.dataCadastroCarro = dataCadastroCarro;
	}
	
	
	

}
