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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAlumno;

	private String apellido1;

	private String apellido2;

	private String cedula;

	private String genero;

	@Column(name="is_active_al")
	private boolean isActiveAl;

	private String nombre;

	//bi-directional many-to-one association to Institucion
	@ManyToOne
	private Institucion institucion;

	//bi-directional many-to-one association to Seccion
	@ManyToOne
	private Seccion seccion;

	//bi-directional many-to-one association to EncargadosAlumno
	@OneToMany(mappedBy="alumno")
	private List<EncargadosAlumno> encargadosAlumnos;

	//bi-directional many-to-one association to HistorialMedico
	@OneToMany(mappedBy="alumno")
	private List<HistorialMedico> historialMedicos;

	public Alumno() {
	}

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

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Seccion getSeccion() {
		return this.seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public List<EncargadosAlumno> getEncargadosAlumnos() {
		return this.encargadosAlumnos;
	}

	public void setEncargadosAlumnos(List<EncargadosAlumno> encargadosAlumnos) {
		this.encargadosAlumnos = encargadosAlumnos;
	}

	public EncargadosAlumno addEncargadosAlumno(EncargadosAlumno encargadosAlumno) {
		getEncargadosAlumnos().add(encargadosAlumno);
		encargadosAlumno.setAlumno(this);

		return encargadosAlumno;
	}

	public EncargadosAlumno removeEncargadosAlumno(EncargadosAlumno encargadosAlumno) {
		getEncargadosAlumnos().remove(encargadosAlumno);
		encargadosAlumno.setAlumno(null);

		return encargadosAlumno;
	}

	public List<HistorialMedico> getHistorialMedicos() {
		return this.historialMedicos;
	}

	public void setHistorialMedicos(List<HistorialMedico> historialMedicos) {
		this.historialMedicos = historialMedicos;
	}

	public HistorialMedico addHistorialMedico(HistorialMedico historialMedico) {
		getHistorialMedicos().add(historialMedico);
		historialMedico.setAlumno(this);

		return historialMedico;
	}

	public HistorialMedico removeHistorialMedico(HistorialMedico historialMedico) {
		getHistorialMedicos().remove(historialMedico);
		historialMedico.setAlumno(null);

		return historialMedico;
	}

}