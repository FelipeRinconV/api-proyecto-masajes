package com.software.masajes.model.consultas.personalizadas;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.ColumnResult;

public class ClienteByTerapeuta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String telefono;
	private String cedula;
	private String nombre;
	private String direccion;
	private String email;
	private String ocupacion;
	private Date fechaNacimiento;

	


	public ClienteByTerapeuta(long id, String telefono, String cedula, String nombre, String direccion,
			String email, String ocupacion, Date fechaNacimiento) {
		super();
		this.id = id;

		this.telefono = telefono;
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;
		this.email = email;
		this.ocupacion = ocupacion;
		this.fechaNacimiento = fechaNacimiento;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}


	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
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


	public String getOcupacion() {
		return ocupacion;
	}


	public void setOcupacion(String ocupacion) {
		this.ocupacion = ocupacion;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	
	 
}
