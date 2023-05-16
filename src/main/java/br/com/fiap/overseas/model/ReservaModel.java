package br.com.fiap.overseas.model;

import java.time.Duration;
import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="tb_reservas")
public class ReservaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@UpdateTimestamp
	private LocalDateTime checkin;
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime checkout;
	
	private double valor;
	
	
	public double calcularValorReserva(CarroModel carro) {
		        long horas = Duration.between(checkin, checkout).toHours();  
		        double valorTotal = horas * carro.getValorHora(); 
		        this.valor = valorTotal;
		        return valor;	
	}


		public Long getId() {
			return id;
		}
	
	
		public void setId(Long id) {
			this.id = id;
		}
	
	
		public LocalDateTime getCheckin() {
			return checkin;
		}
	
	
		public void setCheckin(LocalDateTime checkin) {
			this.checkin = checkin;
		}
	
	
		public LocalDateTime getCheckout() {
			return checkout;
		}
	
	
		public void setCheckout(LocalDateTime checkout) {
			this.checkout = checkout;
		}
	
	
		public double getValor() {
			return valor;
		}
	
		
	
	}
	


