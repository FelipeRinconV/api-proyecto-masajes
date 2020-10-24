package com.software.masajes.dto;

public class SecretarioDto {

	private String nombre;

	private String cedula;

	private String email;

	private double sueldo;

	private String clave;

	public SecretarioDto() {
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

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "SecretarioDto [nombre=" + nombre + ", cedula=" + cedula + ", email=" + email + ", sueldo=" + sueldo
				+ ", clave=" + clave + "]";
	}

}
