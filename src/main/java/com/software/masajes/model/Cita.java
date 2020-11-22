package com.software.masajes.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "citas")
public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cita")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "fecha")
	private Date fechaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "fecha_final")
	private Date fechaFinal;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_secretario")
	private Secretario secretario;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_terapia")
	private Terapia terapia;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_terapeuta")
	private Terapeuta terapeuta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_factura")
	private Factura factura;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name  ="id_cliente")
	private Cliente cliente;
	
	@OneToOne
    @JoinColumn(name="id_observacion",nullable = true)
	private Observacion observacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}


	public Date getFechaFinal() {
		return fechaFinal;
	}



	public Terapia getTerapia() {
		return terapia;
	}

	public void setTerapia(Terapia terapia) {
		this.terapia = terapia;
	}

	public Terapeuta getTerapeuta() {
		return terapeuta;
	}

	public void setTerapeuta(Terapeuta terapeuta) {
		this.terapeuta = terapeuta;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public Secretario getSecretario() {
		return secretario;
	}

	public void setSecretario(Secretario secretario) {
		this.secretario = secretario;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFinal(Date fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Observacion getObservacion() {
		return observacion;
	}

	public void setObservacion(Observacion observacion) {
		this.observacion = observacion;
	}
	
	

}
