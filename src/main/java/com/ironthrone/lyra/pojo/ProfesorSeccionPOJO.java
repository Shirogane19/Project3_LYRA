package com.ironthrone.lyra.pojo;

public class ProfesorSeccionPOJO {
	
	private int idProfesor_Seccion;
	private SeccionPOJO seccion;
	private UsuarioPOJO usuario;
	
	public ProfesorSeccionPOJO(){
		super();
	}

	public int getIdProfesor_Seccion() {
		return idProfesor_Seccion;
	}

	public void setIdProfesor_Seccion(int idProfesor_Seccion) {
		this.idProfesor_Seccion = idProfesor_Seccion;
	}

	public SeccionPOJO getSeccion() {
		return seccion;
	}

	public void setSeccion(SeccionPOJO seccion) {
		this.seccion = seccion;
	}

	public UsuarioPOJO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPOJO usuario) {
		this.usuario = usuario;
	}
	
	
	

}
