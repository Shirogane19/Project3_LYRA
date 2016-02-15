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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idUsuario;

	private String apellido;

	private String cedula;

	@Temporal(TemporalType.DATE)
	@Column(name="date_of_join")
	private Date dateOfJoin;

	private String email;

	@Column(name="is_active_us")
	private boolean isActiveUs;

	private String movil;

	private String nombre;

	private String password;

	private String telefono;

	//bi-directional many-to-one association to EncargadosAlumno
	@OneToMany(mappedBy="usuario")
	private List<EncargadosAlumno> encargadosAlumnos;

	//bi-directional many-to-one association to MateriasProfesor
	@OneToMany(mappedBy="usuario")
	private List<MateriasProfesor> materiasProfesors;

	//bi-directional many-to-one association to ProfesorSeccion
	@OneToMany(mappedBy="usuario")
	private List<ProfesorSeccion> profesorSeccions;

	//bi-directional many-to-one association to RolUsuario
	@OneToMany(mappedBy="usuario")
	private List<RolUsuario> rolUsuarios;

	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="usuario")
	private List<Tarea> tareas;

	//bi-directional many-to-one association to Institucion
	@ManyToOne
	private Institucion institucion;

	public Usuario() {
	}

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

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

}