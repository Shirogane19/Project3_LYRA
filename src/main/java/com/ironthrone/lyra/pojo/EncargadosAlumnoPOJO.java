package com.ironthrone.lyra.pojo;

public class EncargadosAlumnoPOJO {
	
	private int idEncargados_Alumno;
	private AlumnoPOJO alumno;
	private UsuarioPOJO usuario;
	
	public EncargadosAlumnoPOJO(){
		super();
	}

	public int getIdEncargados_Alumno() {
		return idEncargados_Alumno;
	}

	public void setIdEncargados_Alumno(int idEncargados_Alumno) {
		this.idEncargados_Alumno = idEncargados_Alumno;
	}

	public AlumnoPOJO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoPOJO alumno) {
		this.alumno = alumno;
	}

	public UsuarioPOJO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPOJO usuario) {
		this.usuario = usuario;
	}
	
	

}
