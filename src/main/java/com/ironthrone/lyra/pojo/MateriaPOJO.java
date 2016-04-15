package com.ironthrone.lyra.pojo;

import java.util.List;

public class MateriaPOJO {
	
	private int idMateria;
	private boolean isActiveMat;
	private String nombre;
	private InstitucionPOJO institucion;
	private int IdInstitucion;
	private List<UsuarioPOJO> profesorMateria;
	
	public MateriaPOJO(){
		super();
	}

	public int getIdMateria() {
		return idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}

	public boolean isActiveMat() {
		return isActiveMat;
	}

	public void setActiveMat(boolean isActiveMat) {
		this.isActiveMat = isActiveMat;
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

	public int getIdInstitucion() {
		return IdInstitucion;
	}

	public void setIdInstitucion(int idInstitucion) {
		IdInstitucion = idInstitucion;
	}

	public List<UsuarioPOJO> getProfesorMateria() {
		return profesorMateria;
	}

	public void setProfesorMateria(List<UsuarioPOJO> profesorSeccions) {
		this.profesorMateria = profesorSeccions;
	}


}
