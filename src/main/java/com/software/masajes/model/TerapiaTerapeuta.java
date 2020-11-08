package com.software.masajes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "terapia_terapeuta")
public class TerapiaTerapeuta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_terapia_terapeuta")
	private long id;

	@ManyToOne()
	@JoinColumn(name = "id_terapeuta")
	private Terapeuta terapeuta;

	@ManyToOne()
	@JoinColumn(name = "id_terapia")
	private Terapia terapia;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Terapeuta getTerapeuta() {
		return terapeuta;
	}

	public void setTerapeuta(Terapeuta terapeuta) {
		this.terapeuta = terapeuta;
	}

	public Terapia getTerapia() {
		return terapia;
	}

	public void setTerapia(Terapia terapia) {
		this.terapia = terapia;
	}
	
	
	

}
