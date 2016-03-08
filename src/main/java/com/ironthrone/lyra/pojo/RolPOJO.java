package com.ironthrone.lyra.pojo;

import java.util.List;


public class RolPOJO {
	
	private int idRol;
	private String descripcionRol;
	private boolean isActiveRol;
	private String nombreRol;
	private List<UsuarioPOJO> usuarios;
	private List<TareaPOJO> tareas;
	
	public RolPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdRol() {
		return idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}

	public String getDescripcionRol() {
		return descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}

	public boolean isActiveRol() {
		return isActiveRol;
	}

	public void setActiveRol(boolean isActiveRol) {
		this.isActiveRol = isActiveRol;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public List<UsuarioPOJO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioPOJO> usuarios) {
		this.usuarios = usuarios;
	}

	public List<TareaPOJO> getTareas() {
		return tareas;
	}

	public void setTareas(List<TareaPOJO> tareas) {
		this.tareas = tareas;
	}


	

	
}
