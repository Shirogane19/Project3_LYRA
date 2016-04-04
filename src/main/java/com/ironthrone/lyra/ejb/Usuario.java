package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


import java.util.Date;
import java.util.List;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	
	private List<Rol> rols;
	
	private List<Tarea> tareas;
	
	private List<Alumno> alumnos;
	
	private List<Chat> chats;
	
	private List<Institucion> institucions;
	
	private List<Materia> materias;
	
	private List<Seccion> seccions;
	
	private List<Periodo> periodos;

	public Usuario() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}


	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="date_of_join")
	public Date getDateOfJoin() {
		return this.dateOfJoin;
	}

	public void setDateOfJoin(Date dateOfJoin) {
		this.dateOfJoin = dateOfJoin;
	}


	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Column(name="is_active_us")
	public boolean getIsActiveUs() {
		return this.isActiveUs;
	}

	public void setIsActiveUs(boolean isActiveUs) {
		this.isActiveUs = isActiveUs;
	}


	public String getMovil() {
		return this.movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	
	//bi-directional many-to-many association to Usuario
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="rol_usuario"
		, joinColumns={
			@JoinColumn(name="Usuario_idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Rol_idRol")
			}
		)
	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}


	//bi-directional many-to-many association to Tarea
	@ManyToMany(mappedBy="usuarios", fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}


	//bi-directional many-to-many association to Alumno
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="encargados_alumno"
		, joinColumns={
			@JoinColumn(name="Usuario_idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Alumno_idAlumno")
			}
		)
	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}


	//bi-directional many-to-many association to Chat
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="chat_has_usuario"
		, joinColumns={
			@JoinColumn(name="Usuario_idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Chat_idChat")
			}
		)
	public List<Chat> getChats() {
		return this.chats;
	}

	public void setChats(List<Chat> chats) {
		this.chats = chats;
	}


	//bi-directional many-to-many association to Institucion
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="usuarios_institucion"
		, joinColumns={
			@JoinColumn(name="Usuario_idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Institucion_idInstitucion")
			}
		)
	public List<Institucion> getInstitucions() {
		return this.institucions;
	}

	public void setInstitucions(List<Institucion> institucions) {
		this.institucions = institucions;
	}


	//bi-directional many-to-many association to Materia
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="materias_profesor"
		, joinColumns={
			@JoinColumn(name="Usuario_idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Materia_idMateria")
			}
		)
	public List<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	//bi-directional many-to-many association to Periodo

	
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="usuario_has_periodo"
		, joinColumns={
			@JoinColumn(name="usuario_idUsuario")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Periodo_idPeriodo")
			}
		)
	public List<Periodo> getPeriodos() {
		return this.periodos;
	}

	public void setPeriodos(List<Periodo> periodos) {
		this.periodos = periodos;
	}

	//bi-directional many-to-many association to Usuario
		@ManyToMany(mappedBy="usuarios")
	public List<Seccion> getSeccions() {
		return this.seccions;
	}

	public void setSeccions(List<Seccion> seccions) {
		this.seccions = seccions;
	}

}