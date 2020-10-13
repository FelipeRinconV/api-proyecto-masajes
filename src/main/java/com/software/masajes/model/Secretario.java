package com.software.masajes.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "secretarios")
public class Secretario {

    @Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="id_secretario")
	private long id;
	
	@Basic
	private String nombre;
	
	@Column(nullable = false, length = 10)
	private String cedula;
	
	@Column(nullable = false, precision = 2)
	private double sueldo;

	public Secretario() {
		super();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public double getSueldo() {
		return sueldo;
	}

	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}

	
}
