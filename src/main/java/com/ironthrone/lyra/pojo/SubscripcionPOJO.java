package com.ironthrone.lyra.pojo;

import java.util.Date;

public class SubscripcionPOJO {
	
	private int idSubscripcion;
	private Date fechaFin;
	private String fechaFinString;
	private Date fechaInicio;
	private boolean isActiveSub;
	private InstitucionPOJO institucion;
	
	public SubscripcionPOJO(){
		super();
	}

	public int getIdSubscripcion() {
		return idSubscripcion;
	}

	public void setIdSubscripcion(int idSubscripcion) {
		this.idSubscripcion = idSubscripcion;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public boolean isActiveSub() {
		return isActiveSub;
	}

	public void setActiveSub(boolean isActiveSub) {
		this.isActiveSub = isActiveSub;
	}

	public InstitucionPOJO getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionPOJO institucion) {
		this.institucion = institucion;
	}

	public String getFechaFinString() {
		return fechaFinString;
	}

	public void setFechaFinString(String fechaFinString) {
		this.fechaFinString = fechaFinString;
	}
	
	
	
	

}
