package com.ironthrone.lyra.pojo;

import java.util.List;

public class SeccionPOJO {
	
	private int idSeccion;
	private boolean isActiveSec;
	private String nombreSeccion;
	private List<AlumnoPOJO> alumnos;
	private List<ProfesorSeccionPOJO> profesorSeccions;
	private GradoPOJO grado;

	public SeccionPOJO(){
		super();
	}

	public int getIdSeccion() {
		return idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public boolean isActiveSec() {
		return isActiveSec;
	}

	public void setActiveSec(boolean isActiveSec) {
		this.isActiveSec = isActiveSec;
	}

	public String getNombreSeccion() {
		return nombreSeccion;
	}

	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}

	public List<AlumnoPOJO> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnoPOJO> alumnos) {
		this.alumnos = alumnos;
	}

	public List<ProfesorSeccionPOJO> getProfesorSeccions() {
		return profesorSeccions;
	}

	public void setProfesorSeccions(List<ProfesorSeccionPOJO> profesorSeccions) {
		this.profesorSeccions = profesorSeccions;
	}

	public GradoPOJO getGrado() {
		return grado;
	}

	public void setGrado(GradoPOJO grado) {
		this.grado = grado;
	}
	
	
}
