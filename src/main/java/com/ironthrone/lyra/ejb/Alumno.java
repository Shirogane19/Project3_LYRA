package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the alumno database table.
 * 
 */
@Entity
@NamedQuery(name="Alumno.findAll", query="SELECT a FROM Alumno a")
public class Alumno implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idAlumno;
	private String apellido1;
	private String apellido2;
	private String cedula;
	private String genero;
	private boolean isActiveAl;
	private String nombre;
	private Institucion institucion;
	private Seccion seccion;
	private List<RegistrosMedico> registrosMedicos;
	private List<Usuario> usuarios;

	public Alumno() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdAlumno() {
		return this.idAlumno;
	}

	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}


	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getCedula() {
		return this.cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}


	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}


	@Column(name="is_active_al")
	public boolean getIsActiveAl() {
		return this.isActiveAl;
	}

	public void setIsActiveAl(boolean isActiveAl) {
		this.isActiveAl = isActiveAl;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	//bi-directional many-to-one association to Institucion
	@ManyToOne(fetch=FetchType.LAZY)
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}


	//bi-directional many-to-one association to Seccion
	@ManyToOne(fetch=FetchType.LAZY)
	public Seccion getSeccion() {
		return this.seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}


	//bi-directional many-to-one association to RegistrosMedico
	@OneToMany(mappedBy="alumno")
	public List<RegistrosMedico> getRegistrosMedicos() {
		return this.registrosMedicos;
	}

	public void setRegistrosMedicos(List<RegistrosMedico> registrosMedicos) {
		this.registrosMedicos = registrosMedicos;
	}

	public RegistrosMedico addRegistrosMedico(RegistrosMedico registrosMedico) {
		getRegistrosMedicos().add(registrosMedico);
		registrosMedico.setAlumno(this);

		return registrosMedico;
	}

	public RegistrosMedico removeRegistrosMedico(RegistrosMedico registrosMedico) {
		getRegistrosMedicos().remove(registrosMedico);
		registrosMedico.setAlumno(null);

		return registrosMedico;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="alumnos")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}