package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the rol database table.
 * 
 */
@Entity
@NamedQuery(name="Rol.findAll", query="SELECT r FROM Rol r")
public class Rol implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idRol;
	private String descripcionRol;
	private boolean isActiveRol;
	private String nombreRol;
	private List<Tarea> tareas;
	private List<Usuario> usuarios;

	public Rol() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdRol() {
		return this.idRol;
	}

	public void setIdRol(int idRol) {
		this.idRol = idRol;
	}


	@Column(name="descripcion_rol")
	public String getDescripcionRol() {
		return this.descripcionRol;
	}

	public void setDescripcionRol(String descripcionRol) {
		this.descripcionRol = descripcionRol;
	}


	@Column(name="is_active_rol")
	public boolean getIsActiveRol() {
		return this.isActiveRol;
	}

	public void setIsActiveRol(boolean isActiveRol) {
		this.isActiveRol = isActiveRol;
	}


	@Column(name="nombre_rol")
	public String getNombreRol() {
		return this.nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}


	//bi-directional many-to-many association to Tarea
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="tareas_rol"
		, joinColumns={
			@JoinColumn(name="Rol_idRol")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Tarea_idTarea")
			}
		)
	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="rols", fetch = FetchType.LAZY)
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	



}