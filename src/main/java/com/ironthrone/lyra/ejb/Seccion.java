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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSeccion;

	@Column(name="is_active_sec")
	private boolean isActiveSec;

	@Column(name="nombre_seccion")
	private String nombreSeccion;

	//bi-directional many-to-one association to Alumno
	@OneToMany(mappedBy="seccion")
	private List<Alumno> alumnos;

	//bi-directional many-to-one association to ProfesorSeccion
	@OneToMany(mappedBy="seccion")
	private List<ProfesorSeccion> profesorSeccions;

	//bi-directional many-to-one association to Grado
	@ManyToOne
	private Grado grado;

	public Seccion() {
	}

	public int getIdSeccion() {
		return this.idSeccion;
	}

	public void setIdSeccion(int idSeccion) {
		this.idSeccion = idSeccion;
	}

	public boolean getIsActiveSec() {
		return this.isActiveSec;
	}

	public void setIsActiveSec(boolean isActiveSec) {
		this.isActiveSec = isActiveSec;
	}

	public String getNombreSeccion() {
		return this.nombreSeccion;
	}

	public void setNombreSeccion(String nombreSeccion) {
		this.nombreSeccion = nombreSeccion;
	}

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

	public List<ProfesorSeccion> getProfesorSeccions() {
		return this.profesorSeccions;
	}

	public void setProfesorSeccions(List<ProfesorSeccion> profesorSeccions) {
		this.profesorSeccions = profesorSeccions;
	}

	public ProfesorSeccion addProfesorSeccion(ProfesorSeccion profesorSeccion) {
		getProfesorSeccions().add(profesorSeccion);
		profesorSeccion.setSeccion(this);

		return profesorSeccion;
	}

	public ProfesorSeccion removeProfesorSeccion(ProfesorSeccion profesorSeccion) {
		getProfesorSeccions().remove(profesorSeccion);
		profesorSeccion.setSeccion(null);

		return profesorSeccion;
	}

	public Grado getGrado() {
		return this.grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

}