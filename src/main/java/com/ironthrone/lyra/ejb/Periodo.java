package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the periodo database table.
 * 
 */
@Entity
@NamedQuery(name="Periodo.findAll", query="SELECT p FROM Periodo p")
public class Periodo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idPeriodo;
	
	private boolean isActivePr;
	
	private String year;
	
	private List<Alumno> alumnos;
	
	private List<Usuario> usuarios;

	public Periodo() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdPeriodo() {
		return this.idPeriodo;
	}

	public void setIdPeriodo(int idPeriodo) {
		this.idPeriodo = idPeriodo;
	}


	@Column(name="is_active_pr")
	public boolean getIsActivePr() {
		return this.isActivePr;
	}

	public void setIsActivePr(boolean isActivePr) {
		this.isActivePr = isActivePr;
	}


	public String getYear() {
		return this.year;
	}

	public void setYear(String year) {
		this.year = year;
	}


	//bi-directional many-to-many association to Alumno
	@ManyToMany
	@JoinTable(
		name="alumno_has_periodo"
		, joinColumns={
			@JoinColumn(name="Periodo_idPeriodo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="alumno_idAlumno")
			}
		)
	public List<Alumno> getAlumnos() {
		return this.alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany
	@JoinTable(
		name="usuario_has_periodo"
		, joinColumns={
			@JoinColumn(name="Periodo_idPeriodo")
			}
		, inverseJoinColumns={
			@JoinColumn(name="usuario_idUsuario")
			}
		)
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}