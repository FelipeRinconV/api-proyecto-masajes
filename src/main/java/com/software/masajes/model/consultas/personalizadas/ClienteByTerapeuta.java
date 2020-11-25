package com.software.masajes.model.consultas.personalizadas;

import java.io.Serializable;

public class ClienteByTerapeuta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private String cliente;
	private String telefono;
	private String cedula;

	
	
	

	
	public ClienteByTerapeuta(long id, String cliente, String telefono, String cedula) {
		super();
		this.id = id;
		this.cliente = cliente;
		this.telefono = telefono;
		this.cedula = cedula;

	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
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

	
	
	
	 
}
