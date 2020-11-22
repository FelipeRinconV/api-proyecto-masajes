package com.software.masajes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "terapias")
public class Terapia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_terapia")
	private long id;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "terapia", cascade = CascadeType.REMOVE, orphanRemoval = true)
	private List<TerapiaTerapeuta> terapiaTerapeutas;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_secretario")
	private Secretario secretario;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "terapia")
	private List<Cita> citas;

	@Column(nullable = false, name = "nombre")
	private String nombre;

	@Column(nullable = false, name = "precio")
	private double precio;

	@Column(nullable = false, name = "duracion_minutos")
	private int duracionMinutos;

	public Terapia() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public List<TerapiaTerapeuta> getTerapiaTerapeutas() {
		return terapiaTerapeutas;
	}

	public void setTerapiaTerapeutas(List<TerapiaTerapeuta> terapiaTerapeutas) {
		this.terapiaTerapeutas = terapiaTerapeutas;
	}

	public Secretario getSecretario() {
		return secretario;
	}

	public void setSecretario(Secretario secretario) {
		this.secretario = secretario;
	}

	public Secretario getDescretario() {
		return secretario;
	}

	public void setDescretario(Secretario descretario) {
		this.secretario = descretario;
	}

}
