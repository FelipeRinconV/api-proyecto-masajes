package com.software.masajes.dto;

public class CitaDto {

	private String fechaInicio;

	private String horaInicial;
	
	private int duracion;

	private int idSecretario;

	private int idTerapia;

	private int idTerapeuta;
	
	private int idCliente;
	

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	public int getIdTerapia() {
		return idTerapia;
	}

	public void setIdTerapia(int idTerapia) {
		this.idTerapia = idTerapia;
	}

	public int getIdSecretario() {
		return idSecretario;
	}

	public void setIdSecretario(int idSecretario) {
		this.idSecretario = idSecretario;
	}

	public int getIdTerapeuta() {
		return idTerapeuta;
	}

	public void setIdTerapeuta(int idTerapeuta) {
		this.idTerapeuta = idTerapeuta;
	}

	public int getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(int idFactura) {
		this.idFactura = idFactura;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getHoraInicial() {
		return horaInicial;
	}

	public void setHoraInicial(String horaInicial) {
		this.horaInicial = horaInicial;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
	
	


}
