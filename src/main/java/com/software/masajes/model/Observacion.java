
package com.software.masajes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;






@NamedQueries({
	 @NamedQuery(name = Observacion.LISTAR_OBSERVACIONES_POR_CITA,query = "SELECT c FROM Observacion o WHERE  c.Cita:=Cita" )
})
@Entity
@Table(name = "observaciones")
public class Observacion implements Serializable {
	
	
	
	public  static final String LISTAR_OBSERVACIONES_POR_CITA="LISTAR_OBERSVACIONES_POR_CITA";
	
	
	
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
	

	@OneToOne()
    private Cita cita;
	
	@Column(name = "observacion",nullable = false)
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
