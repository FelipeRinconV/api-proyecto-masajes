package com.software.masajes.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@NamedQueries({

		@NamedQuery(name = Secretario.LOG_SECRETARIO, query = "select u from Secretario u where u.email=:email and u.clave=:clave")

})
@Entity
@Table(name = "secretarios")
public class Secretario implements Serializable {

	/**
	 * Devuelve el secretario si lo encuentra con el correo y clave indicada
	 */
	public static final String LOG_SECRETARIO = "numero_de_registro_por_usuario";

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id_secretario")
	private long id;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "secretario")
	private List<Cita> citas;

	@OneToMany(fetch = FetchType.LAZY,mappedBy = "secretario")
	private List<Terapia> terapias;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String cedula;

	@Column(nullable = false)
	private double sueldo;

	@Column(nullable = false)
	private String clave;

	public Secretario() {
		super();
	}

	public Secretario(long id, String nombre, String cedula, double sueldo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.cedula = cedula;
		this.sueldo = sueldo;
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}

}
