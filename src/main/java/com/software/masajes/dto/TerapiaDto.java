package com.software.masajes.dto;

public class TerapiaDto {
	
	private int idSecretario;	
	
	private String nombre;
	
	private double precio;
	
	private int duracionMinutos;

	public int getIdSecretario() {
		return idSecretario;
	}

	public void setIdSecretario(int idSecretario) {
		this.idSecretario = idSecretario;
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

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(int duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	
}
