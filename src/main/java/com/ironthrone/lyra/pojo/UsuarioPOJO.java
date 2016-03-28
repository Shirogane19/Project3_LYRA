package com.ironthrone.lyra.pojo;

import java.util.Date;
import java.util.List;

/**
 * Clase Plain Old Java Object de usuario.
 * @author jeanpaul
 *
 */

public class UsuarioPOJO {
	
	private int idUsuario;
	
	private String apellido;
	
	private String cedula;
	
	private Date dateOfJoin;
	
	private String email;
	
	private boolean isActiveUs;
	
	private String movil;
	
	private String nombre;
	
	private String password;
	
	private String telefono;
	
	private boolean accOwner;

	private boolean newPass;
	
//	private List<AlumnoPOJO> alumnos;
//	
//	private List<MateriaPOJO> materias;
//	
//	private List<SeccionPOJO> seccions;
	
	private List<String> idRoles;
	
	private List<RolPOJO> rols;
	
	
	private List<TareaPOJO> tareas;
	
	private List<String> idTareas;
	
	private InstitucionPOJO institucion;
	
	private List<InstitucionPOJO> listaInstituciones;
	
	private int idInstitucion;
	
	private String periodoYear;
	
	private PeriodoPOJO periodo;
	
	public UsuarioPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Date getDateOfJoin() {
		return dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isActiveUs() {
		return isActiveUs;
	}

	public void setActiveUs(boolean isActiveUs) {
		this.isActiveUs = isActiveUs;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

//	public List<AlumnoPOJO> getAlumnos() {
//		return alumnos;
//	}
//
//	public void setAlumnos(List<AlumnoPOJO> alumnos) {
//		this.alumnos = alumnos;
//	}
//
//	public List<MateriaPOJO> getMaterias() {
//		return materias;
//	}
//
//	public void setMaterias(List<MateriaPOJO> materias) {
//		this.materias = materias;
//	}
//
//	public List<SeccionPOJO> getSeccions() {
//		return seccions;
//	}
//
//	public void setSeccions(List<SeccionPOJO> seccions) {
//		this.seccions = seccions;
//	}

	public List<RolPOJO> getRols() {
		return rols;
	}

	public void setRols(List<RolPOJO> rols) {
		this.rols = rols;
	}

	public List<TareaPOJO> getTareas() {
		return tareas;
	}

	public void setTareas(List<TareaPOJO> tareas) {
		this.tareas = tareas;
	}

	public InstitucionPOJO getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionPOJO institucion) {
		this.institucion = institucion;
	}


	public List<String> getIdRoles() {
		return idRoles;
	}


	public void setIdRoles(List<String> idRoles) {
		this.idRoles = idRoles;
	}


	public boolean isAccOwner() {
		return accOwner;
	}


	public void setAccOwner(boolean isAccOwner) {
		this.accOwner = isAccOwner;
	}


	public boolean isNewPass() {
		return newPass;
	}


	public void setNewPass(boolean newPass) {
		this.newPass = newPass;
	}

	public int getIdInstitucion() {
		return idInstitucion;
	}


	public void setIdInstitucion(int idInstitucion) {
		this.idInstitucion = idInstitucion;
	}


	public List<InstitucionPOJO> getListaInstituciones() {
		return listaInstituciones;
	}


	public void setListaInstituciones(List<InstitucionPOJO> listaInstituciones) {
		this.listaInstituciones = listaInstituciones;
	}


	public PeriodoPOJO getPeriodo() {
		return periodo;
	}


	public void setPeriodo(PeriodoPOJO periodo) {
		this.periodo = periodo;
	}


	public String getPeriodoYear() {
		return periodoYear;
	}


	public void setPeriodoYear(String periodoYear) {
		this.periodoYear = periodoYear;
	}


	public List<String> getIdTareas() {
		return idTareas;
	}


	public void setIdTareas(List<String> idTareas) {
		this.idTareas = idTareas;
	}
	

}
