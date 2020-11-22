package com.software.masajes.dto;

public class ObservacionDto {
	
	
	private String observacion;
	
	private long idCita;
	
	private long idTerapeuta;

	public ObservacionDto() {
		super();
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public long getIdCita() {
		return idCita;
	}

	public void setIdCita(long idCita) {
		this.idCita = idCita;
	}

	public long getIdTerapeuta() {
		return idTerapeuta;
	}

	public void setIdTerapeuta(long idTerapeuta) {
		this.idTerapeuta = idTerapeuta;
	}
	
	

}
