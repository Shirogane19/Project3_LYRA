package com.ironthrone.lyra.pojo;


import java.util.List;

public class CategoriaPOJO {
	private int idCategoria;
	private String descripcionCategoria;
	private boolean isActiveCat;
	private String nombreCategoria;
	private int tarea_idTarea;
	private List<TareaPOJO> tareas;
	private int idInstitucion;
	
	
	public CategoriaPOJO() {
		// TODO Auto-generated constructor stub
	}
	public int getIdCategoria() {
		return idCategoria;
	}

	public String getDescripcionCategoria() {
		return descripcionCategoria;
	}

	public boolean isActiveCat() {
		return isActiveCat;
	}

	public String getNombreCategoria() {
		return nombreCategoria;
	}

	public int getTarea_idTarea() {
		return tarea_idTarea;
	}

	public List<TareaPOJO> getTareas() {
		return tareas;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}

	public void setActiveCat(boolean isActiveCat) {
		this.isActiveCat = isActiveCat;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	public void setTarea_idTarea(int tarea_idTarea) {
		this.tarea_idTarea = tarea_idTarea;
	}

	public void setTareas(List<TareaPOJO> tareas) {
		this.tareas = tareas;
	}
	public int getIdInstitucion() {
		return idInstitucion;
	}
	public void setIdInstitucion(int idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	

}
