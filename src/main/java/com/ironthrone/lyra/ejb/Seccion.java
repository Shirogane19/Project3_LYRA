package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the seccion database table.
 * 
 */
@Entity
@NamedQuery(name="Seccion.findAll", query="SELECT s FROM Seccion s")
public class Seccion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idSeccion;
	private boolean isActiveSec;
	private String nombreSeccion;
	private List<Alumno> alumnos;
	private Grado grado;
	private List<Usuario> usuarios;

	public Seccion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdSeccion() {
		return this.idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}


	@Column(name="is_active_sec")
	public boolean getIsActiveSec() {
		return this.isActiveSec;
	}

	public void setIsActiveSec(boolean isActiveSec) {
		this.isActiveSec = isActiveSec;
	}


	@Column(name="nombre_seccion")
	public String getNombreSeccion() {
		return this.nombreSeccion;
	}

	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}


	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="seccion")
	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}

	public Alumno addAlumno(Alumno alumno) {
		getAlumnos().add(alumno);
		alumno.setSeccion(this);

		return alumno;
	}

	public Alumno removeAlumno(Alumno alumno) {
		getAlumnos().remove(alumno);
		alumno.setSeccion(null);

		return alumno;
	}


	//bi-directional many-to-one association to Grado
	@ManyToOne(fetch=FetchType.LAZY)
	public Grado getGrado() {
		return this.grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="seccions")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}