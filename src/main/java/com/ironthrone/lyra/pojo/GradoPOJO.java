package com.ironthrone.lyra.pojo;

import java.util.Date;
import java.util.List;

import com.ironthrone.lyra.ejb.Institucion;

public class GradoPOJO {
	
	private int idGrado;
	private String descripcion;
	private boolean isActiveGr;
	private String nombre;
	private Date year;
	private Institucion institucion;
	private List<MateriasGradoPOJO> materiasGrados;
	private List<SeccionPOJO> seccions;

	public GradoPOJO(){
		super();
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

	public boolean isActiveGr() {
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

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Institucion getInstitucion() {
		return institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public List<MateriasGradoPOJO> getMateriasGrados() {
		return materiasGrados;
	}

	public void setMateriasGrados(List<MateriasGradoPOJO> materiasGrados) {
		this.materiasGrados = materiasGrados;
	}

	public List<SeccionPOJO> getSeccions() {
		return seccions;
	}

	public void setSeccions(List<SeccionPOJO> seccions) {
		this.seccions = seccions;
	}
	
	
}
