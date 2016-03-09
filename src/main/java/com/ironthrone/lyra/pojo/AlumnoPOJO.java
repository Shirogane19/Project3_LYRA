package com.ironthrone.lyra.pojo;


public class AlumnoPOJO {
	
	private int idAlumno;
	private String apellido1;
	private String apellido2;
	private String cedula;
	private String genero;
	private boolean isActiveAl;
	private String nombre;
	private InstitucionPOJO institucion;
	private SeccionPOJO seccion;

	
	public AlumnoPOJO(){
		super();
	}

	public int getIdAlumno() {
		return idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public boolean isActiveAl() {
		return isActiveAl;
	}

	public void setActiveAl(boolean isActiveAl) {
		this.isActiveAl = isActiveAl;
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

	public SeccionPOJO getSeccion() {
		return seccion;
	}

	public void setSeccion(SeccionPOJO seccion) {
		this.seccion = seccion;
	}


	
	
}
