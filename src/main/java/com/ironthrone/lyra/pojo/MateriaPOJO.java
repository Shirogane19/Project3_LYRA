package com.ironthrone.lyra.pojo;


public class MateriaPOJO {
	
	private int idMateria;
	private boolean isActiveMat;
	private String nombre;
	private InstitucionPOJO institucion;
	private int IdInstitucion;

	
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


}
