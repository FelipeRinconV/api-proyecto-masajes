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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@NamedQueries({
	 @NamedQuery(name = Cita.LISTAR_CITAS_ORDENADAS_POR_FECHA,query = "SELECT c FROM Cita c ORDER BY c.fechaInicio" ),
	
})
@NamedNativeQueries({
	@NamedNativeQuery(name = Cita.LISTAR_CITAS_BY_TERAPEUTA,query = "SELECT * FROM citas WHERE citas.id_terapeuta=?",resultClass = Cita.class),
    @NamedNativeQuery(name = Cita.LISTAR_CITAS_BY_CLIENTE,query = "SELECT * FROM citas WHERE citas.id_cliente=?",resultClass = Cita.class)
})
@Entity
@Table(name = "citas")
public class Cita implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String  LISTAR_CITAS_ORDENADAS_POR_FECHA="LISTA_FECHAS_ORDENADAS";
	public static final String  LISTAR_CITAS_BY_TERAPEUTA="LISTA_citas_terapeutas";
	public static final String  LISTAR_CITAS_BY_CLIENTE="LISTA_CITAS_POR_CLIENTE";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_cita")
	private Long id;

	
	@Column(name = "estado_pago", nullable = false)
	private int estadoPago;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "fecha")
	private Date fechaInicio;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false, name = "fecha_final")
	private Date fechaFinal;

	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
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
	@JoinColumn(name  ="id_cliente")
	private Cliente cliente;
	

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

	

}
