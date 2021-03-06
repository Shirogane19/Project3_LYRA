package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the institucion database table.
 * 
 */
@Entity
@NamedQuery(name="Institucion.findAll", query="SELECT i FROM Institucion i")
public class Institucion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idInstitucion;
	private boolean hasSuscripcion;
	private String logoInstitucion;
	private String nombreInstitucion;
	private List<Alumno> alumnos;
	private List<Bitacora> bitacoras;
	private List<Grado> grados;
	private List<Materia> materias;
	private List<Subscripcion> subscripcions;
	private List<Usuario> usuarios;

	public Institucion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdInstitucion() {
		return this.idInstitucion;
	}

	public void setIdInstitucion(int idInstitucion) {
		this.idInstitucion = idInstitucion;
	}


	@Column(name="has_suscripcion")
	public boolean getHasSuscripcion() {
		return this.hasSuscripcion;
	}

	public void setHasSuscripcion(boolean hasSuscripcion) {
		this.hasSuscripcion = hasSuscripcion;
	}


	@Column(name="logo_institucion")
	public String getLogoInstitucion() {
		return this.logoInstitucion;
	}

	public void setLogoInstitucion(String logoInstitucion) {
		this.logoInstitucion = logoInstitucion;
	}


	@Column(name="nombre_institucion")
	public String getNombreInstitucion() {
		return this.nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}


	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="institucion",fetch = FetchType.LAZY)
	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Alumno addAlumno(Alumno alumno) {
		getAlumnos().add(alumno);
		alumno.setInstitucion(this);

		return alumno;
	}

	public Alumno removeAlumno(Alumno alumno) {
		getAlumnos().remove(alumno);
		alumno.setInstitucion(null);

		return alumno;
	}


	//bi-directional many-to-one association to Bitacora
	@OneToMany(mappedBy="institucion",fetch = FetchType.LAZY)
	public List<Bitacora> getBitacoras() {
		return this.bitacoras;
	}

	public void setBitacoras(List<Bitacora> bitacoras) {
		this.bitacoras = bitacoras;
	}

	public Bitacora addBitacora(Bitacora bitacora) {
		getBitacoras().add(bitacora);
		bitacora.setInstitucion(this);

		return bitacora;
	}

	public Bitacora removeBitacora(Bitacora bitacora) {
		getBitacoras().remove(bitacora);
		bitacora.setInstitucion(null);

		return bitacora;
	}


	//bi-directional many-to-one association to Grado
	@OneToMany(mappedBy="institucion",fetch = FetchType.LAZY)
	public List<Grado> getGrados() {
		return this.grados;
	}

	public void setGrados(List<Grado> grados) {
		this.grados = grados;
	}

	public Grado addGrado(Grado grado) {
		getGrados().add(grado);
		grado.setInstitucion(this);

		return grado;
	}

	public Grado removeGrado(Grado grado) {
		getGrados().remove(grado);
		grado.setInstitucion(null);

		return grado;
	}


	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="institucion",fetch = FetchType.LAZY)
	public List<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
	}

	public Materia addMateria(Materia materia) {
		getMaterias().add(materia);
		materia.setInstitucion(this);

		return materia;
	}

	public Materia removeMateria(Materia materia) {
		getMaterias().remove(materia);
		materia.setInstitucion(null);

		return materia;
	}


	//bi-directional many-to-one association to Subscripcion
	@OneToMany(mappedBy="institucion",fetch = FetchType.LAZY)
	public List<Subscripcion> getSubscripcions() {
		return this.subscripcions;
	}

	public void setSubscripcions(List<Subscripcion> subscripcions) {
		this.subscripcions = subscripcions;
	}

	public Subscripcion addSubscripcion(Subscripcion subscripcion) {
		getSubscripcions().add(subscripcion);
		subscripcion.setInstitucion(this);

		return subscripcion;
	}

	public Subscripcion removeSubscripcion(Subscripcion subscripcion) {
		getSubscripcions().remove(subscripcion);
		subscripcion.setInstitucion(null);

		return subscripcion;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="institucions",fetch = FetchType.LAZY)
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}