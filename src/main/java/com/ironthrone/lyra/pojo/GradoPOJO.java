package com.ironthrone.lyra.pojo;


import java.util.List;

/**
 * Clase Plain Old Java Object de grado.
 * @author jeanpaul
 *
 */
public class GradoPOJO {

	private int idGrado;
	
	private String descripcion;
	
	private boolean isActiveGr;
	
	private String nombre;
	
	private InstitucionPOJO institucion;
	
	private List<MateriaPOJO> materias;
	
	private List<SeccionPOJO> seccions;
	
	private List<String> idMaterias;
	
	private List<String> idSecciones;

	public GradoPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIdGrado() {
		return idGrado;
	}

	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean getIsActiveGr() {
		return isActiveGr;
	}

	public void setActiveGr(boolean isActiveGr) {
		this.isActiveGr = isActiveGr;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public InstitucionPOJO getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionPOJO institucion) {
		this.institucion = institucion;
	}

	public List<MateriaPOJO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaPOJO> materias) {
		this.materias = materias;
	}

	public List<SeccionPOJO> getSeccions() {
		return seccions;
	}

	public void setSeccions(List<SeccionPOJO> seccions) {
		this.seccions = seccions;
	}

	public List<String> getIdMaterias() {
		return idMaterias;
	}

	public void setIdMaterias(List<String> idMaterias) {
		this.idMaterias = idMaterias;
	}

	public List<String> getIdSecciones() {
		return idSecciones;
	}

	public void setIdSecciones(List<String> idSecciones) {
		this.idSecciones = idSecciones;
	}
	
	
	
	
}
