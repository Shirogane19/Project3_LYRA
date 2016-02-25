package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tareas_rol database table.
 * 
 */
@Entity
@Table(name="tareas_rol")
@NamedQuery(name="TareasRol.findAll", query="SELECT t FROM TareasRol t")
public class TareasRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTareas_Rol;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	private Rol rol;

	//bi-directional many-to-one association to Tarea
	@ManyToOne
	private Tarea tarea;

	public TareasRol() {
	}

	public int getIdTareas_Rol() {
		return this.idTareas_Rol;
	}

	public void setIdTareas_Rol(int idTareas_Rol) {
		this.idTareas_Rol = idTareas_Rol;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Tarea getTarea() {
		return this.tarea;
	}

	public void setTarea(Tarea tarea) {
		this.tarea = tarea;
	}

}