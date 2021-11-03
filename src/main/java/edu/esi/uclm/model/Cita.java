package edu.esi.uclm.model;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Cita {
	private String fecha;
	private String hora;
	private String usuarioDni;
	@DBRef
	private CentroVacunacion centroVacunacion;

	public Cita() {
		// El constructor vacio ha sido crado por exigencias del Spring
	}

	public Cita(String fecha, String hora, CentroVacunacion centro, String usuarioDni) {
		this.fecha = fecha;
		this.hora = hora;
		this.centroVacunacion = centro;
		this.usuarioDni = usuarioDni;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public CentroVacunacion getCentroVacunacion() {
		return centroVacunacion;
	}

	public void setCentroVacunacion(CentroVacunacion centroVacunacion) {
		this.centroVacunacion = centroVacunacion;
	}

	public String getUsuarioDni() {
		return usuarioDni;
	}

	public void setUsuarioDni(String usuarioDni) {
		this.usuarioDni = usuarioDni;
	}

}
