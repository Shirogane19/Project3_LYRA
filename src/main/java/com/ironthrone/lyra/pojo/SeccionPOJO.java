package com.ironthrone.lyra.pojo;

import java.util.List;

public class SeccionPOJO {
	
	private int idSeccion;
	private boolean isActiveSec;
	private String nombreSeccion;
	private List<AlumnoPOJO> alumnos;
	private List<UsuarioPOJO> profesorSeccions;
	private GradoPOJO grado;
	private List<Integer> idProfesores;
	private List<Integer> idAlumnos;

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

	public List<UsuarioPOJO> getProfesorSeccions() {
		return profesorSeccions;
	}

	public void setProfesorSeccions(List<UsuarioPOJO> profesorSeccions) {
		this.profesorSeccions = profesorSeccions;
	}

	public GradoPOJO getGrado() {
		return grado;
	}

	public void setGrado(GradoPOJO grado) {
		this.grado = grado;
	}

	public List<Integer> getIdProfesores() {
		return idProfesores;
	}

	public void setIdProfesores(List<Integer> idProfesores) {
		this.idProfesores = idProfesores;
	}

	public List<Integer> getIdAlumnos() {
		return idAlumnos;
	}

	public void setIdAlumnos(List<Integer> idAlumnos) {
		this.idAlumnos = idAlumnos;
	}
	
	

}
