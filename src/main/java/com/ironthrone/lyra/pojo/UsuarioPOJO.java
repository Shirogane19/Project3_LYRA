package com.ironthrone.lyra.pojo;

import java.util.Date;
import java.util.List;




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

	private List<EncargadosAlumnoPOJO> encargados;
	
	private List<MateriasProfesorPOJO> materiasProfesores;
	
	private List<ProfesorSeccionPOJO> seccionesProfesores;
	
	private List<RolUsuarioPOJO> rolesUsuario;
	
	private List<TareaPOJO> tareasUsuario;
	
	private InstitucionPOJO institucion;
	
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

	public List<EncargadosAlumnoPOJO> getEncargados() {
		return encargados;
	}

	public void setEncargados(List<EncargadosAlumnoPOJO> encargados) {
		this.encargados = encargados;
	}

	public List<MateriasProfesorPOJO> getMateriasProfesores() {
		return materiasProfesores;
	}

	public void setMateriasProfesores(List<MateriasProfesorPOJO> materiasProfesores) {
		this.materiasProfesores = materiasProfesores;
	}

	public List<ProfesorSeccionPOJO> getSeccionesProfesores() {
		return seccionesProfesores;
	}

	public void setSeccionesProfesores(List<ProfesorSeccionPOJO> seccionesProfesores) {
		this.seccionesProfesores = seccionesProfesores;
	}

	public List<RolUsuarioPOJO> getRolesUsuario() {
		return rolesUsuario;
	}

	public void setRolesUsuario(List<RolUsuarioPOJO> rolesUsuario) {
		this.rolesUsuario = rolesUsuario;
	}

	public List<TareaPOJO> getTareasUsuario() {
		return tareasUsuario;
	}

	public void setTareasUsuario(List<TareaPOJO> tareasUsuario) {
		this.tareasUsuario = tareasUsuario;
	}

	public InstitucionPOJO getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionPOJO institucion) {
		this.institucion = institucion;
	}



}
