
package com.software.masajes.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "observaciones")
public class Observacion implements Serializable {
	
	/**
	 *
	 *
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_observacion")
	private Long id;
	public Long getId() {
		return id;
	}
	
	
	@OneToOne(mappedBy="observacion")
    private Cita cita;
	
	@Basic
	private String observacion;
	
	@ManyToOne
	@JoinColumn(name="id_observacion_terapeuta")
	private Terapeuta terapeuta;
	
	public void setId(Long id) {
		this.id = id;
	}


	public Cita getCita() {
		return cita;
	}



	public void setCita(Cita cita) {
		this.cita = cita;
	}



	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Terapeuta getTerapeuta() {
		return terapeuta;
	}

	public void setTerapeuta(Terapeuta terapeuta) {
		this.terapeuta = terapeuta;
	}
	
		
	
	
}
