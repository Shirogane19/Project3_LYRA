package com.ironthrone.lyra.pojo;



public class RegistroMedicoPOJO {
	
	
	private int idRegistro;
	private String descripcion;
	private String nombreRegistro;
	private AlumnoPOJO alumno;
	private int idAlumno;
	private boolean onPoint = false;
	
	public RegistroMedicoPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistros_Medicos) {
		this.idRegistro = idRegistros_Medicos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreRegistro() {
		return nombreRegistro;
	}

	public void setNombreRegistro(String nombreRegistro) {
		this.nombreRegistro = nombreRegistro;
	}

	public AlumnoPOJO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoPOJO alumno) {
		this.alumno = alumno;
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public boolean isOnPoint() {
		return onPoint;
	}

	public void setOnPoint(boolean onPoint) {
		this.onPoint = onPoint;
	}
	
	
	
	

}
