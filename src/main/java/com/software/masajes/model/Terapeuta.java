package com.software.masajes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({

		@NamedQuery(name = Terapeuta.LOG_TERAPEUTA, query = "select u from Secretario u where u.email=:email and u.clave=:clave")

})

@Entity
@Table(name = "terapeutas")
public class Terapeuta implements Serializable {

	public static final String LOG_TERAPEUTA = "login_terapeuta";

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_terapeuta")
	private long id;

	@Column(nullable = false, name = "cedula", length = 10)
	private String cedula;

	@Column(nullable = false, name = "clave")
	private String clave;

	@Column(nullable = true, name = "direccion")
	private String direccion;

	@Column(nullable = false, name = "email")
	private String email;

	@Column(nullable = false, name = "nombre")
	private String nombre;

	@Column(nullable = false, name = "profesion")
	private String profesion;

	public Terapeuta() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProfesion() {
		return profesion;
	}

	public void setProfesion(String profesion) {
		this.profesion = profesion;
	}

}
