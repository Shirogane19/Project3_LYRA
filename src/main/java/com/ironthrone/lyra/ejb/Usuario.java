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
	
	private List<EncargadosAlumno> encargadosAlumnos;
	
	private List<MateriasProfesor> materiasProfesors;
	
	private List<ProfesorSeccion> profesorSeccions;
	
	private List<RolUsuario> rolUsuarios;
	
	private List<Tarea> tareas;
	
	private Institucion institucion;
	
//	private List<Chat> chats;

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


	//bi-directional many-to-one association to EncargadosAlumno
	@OneToMany(mappedBy="usuario")
	public List<EncargadosAlumno> getEncargadosAlumnos() {
		return this.encargadosAlumnos;
	}

	public void setEncargadosAlumnos(List<EncargadosAlumno> encargadosAlumnos) {
		this.encargadosAlumnos = encargadosAlumnos;
	}

	public EncargadosAlumno addEncargadosAlumno(EncargadosAlumno encargadosAlumno) {
		getEncargadosAlumnos().add(encargadosAlumno);
		encargadosAlumno.setUsuario(this);

		return encargadosAlumno;
	}

	public EncargadosAlumno removeEncargadosAlumno(EncargadosAlumno encargadosAlumno) {
		getEncargadosAlumnos().remove(encargadosAlumno);
		encargadosAlumno.setUsuario(null);

		return encargadosAlumno;
	}


	//bi-directional many-to-one association to MateriasProfesor
	@OneToMany(mappedBy="usuario")
	public List<MateriasProfesor> getMateriasProfesors() {
		return this.materiasProfesors;
	}

	public void setMateriasProfesors(List<MateriasProfesor> materiasProfesors) {
		this.materiasProfesors = materiasProfesors;
	}

	public MateriasProfesor addMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().add(materiasProfesor);
		materiasProfesor.setUsuario(this);

		return materiasProfesor;
	}

	public MateriasProfesor removeMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().remove(materiasProfesor);
		materiasProfesor.setUsuario(null);

		return materiasProfesor;
	}


	//bi-directional many-to-one association to ProfesorSeccion
	@OneToMany(mappedBy="usuario")
	public List<ProfesorSeccion> getProfesorSeccions() {
		return this.profesorSeccions;
	}

	public void setProfesorSeccions(List<ProfesorSeccion> profesorSeccions) {
		this.profesorSeccions = profesorSeccions;
	}

	public ProfesorSeccion addProfesorSeccion(ProfesorSeccion profesorSeccion) {
		getProfesorSeccions().add(profesorSeccion);
		profesorSeccion.setUsuario(this);

		return profesorSeccion;
	}

	public ProfesorSeccion removeProfesorSeccion(ProfesorSeccion profesorSeccion) {
		getProfesorSeccions().remove(profesorSeccion);
		profesorSeccion.setUsuario(null);

		return profesorSeccion;
	}


	//bi-directional many-to-one association to RolUsuario
	@OneToMany(mappedBy="usuario")
	public List<RolUsuario> getRolUsuarios() {
		return this.rolUsuarios;
	}

	public void setRolUsuarios(List<RolUsuario> rolUsuarios) {
		this.rolUsuarios = rolUsuarios;
	}

	public RolUsuario addRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().add(rolUsuario);
		rolUsuario.setUsuario(this);

		return rolUsuario;
	}

	public RolUsuario removeRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().remove(rolUsuario);
		rolUsuario.setUsuario(null);

		return rolUsuario;
	}


	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="usuario")
	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Tarea addTarea(Tarea tarea) {
		getTareas().add(tarea);
		tarea.setUsuario(this);

		return tarea;
	}

	public Tarea removeTarea(Tarea tarea) {
		getTareas().remove(tarea);
		tarea.setUsuario(null);

		return tarea;
	}


	//bi-directional many-to-one association to Institucion
	@ManyToOne
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}


//	//bi-directional many-to-many association to Chat
//	@ManyToMany
//	@JoinTable(
//		name="chat_has_usuario"
//		, joinColumns={
//			@JoinColumn(name="Usuario_idUsuario")
//			}
//		, inverseJoinColumns={
//			@JoinColumn(name="Chat_idChat")
//			}
//		)
//	public List<Chat> getChats() {
//		return this.chats;
//	}
//
//	public void setChats(List<Chat> chats) {
//		this.chats = chats;
//	}

}