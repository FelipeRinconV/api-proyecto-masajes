package com.software.masajes.dto;

public class TerapeutaOuputDto {
	
	
	private long id;
	
	private String cedula;

	private String clave;

	private String direccion;
	
	private String email;

	private String nombre;

	private String profesion;

	
	
	public TerapeutaOuputDto(long id, String cedula, String email, String nombre,
			String profesion) {
		super();
		this.id = id;
		this.cedula = cedula;
		this.email = email;
		this.nombre = nombre;
		this.profesion = profesion;
	}

	public TerapeutaOuputDto() {
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
