package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


import java.util.List;


/**
 * The persistent class for the categoria database table.
 * 
 */
@Entity
@NamedQuery(name="Categoria.findAll", query="SELECT c FROM Categoria c")
public class Categoria implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int idCategoria;
	
	private String descripcionCategoria;
	
	private boolean isActiveCat;
	
	private String nombreCategoria;
	
	private int tarea_idTarea;
	
	private Institucion institucion;
	
	private List<Tarea> tareas;

	public Categoria() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdCategoria() {
		return this.idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}


	@Column(name="descripcion_categoria")
	public String getDescripcionCategoria() {
		return this.descripcionCategoria;
	}

	public void setDescripcionCategoria(String descripcionCategoria) {
		this.descripcionCategoria = descripcionCategoria;
	}


	@Column(name="is_active_cat")
	public boolean getIsActiveCat() {
		return this.isActiveCat;
	}

	public void setIsActiveCat(boolean isActiveCat) {
		this.isActiveCat = isActiveCat;
	}


	@Column(name="nombre_categoria")
	public String getNombreCategoria() {
		return this.nombreCategoria;
	}

	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}


	public int getTarea_idTarea() {
		return this.tarea_idTarea;
	}

	public void setTarea_idTarea(int tarea_idTarea) {
		this.tarea_idTarea = tarea_idTarea;
	}


	//bi-directional many-to-one association to Institucion
	@ManyToOne(fetch=FetchType.LAZY)
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}


	//bi-directional many-to-one association to Tarea
	@OneToMany(mappedBy="categoria")
	public List<Tarea> getTareas() {
		return this.tareas;
	}

	public void setTareas(List<Tarea> tareas) {
		this.tareas = tareas;
	}

	public Tarea addTarea(Tarea tarea) {
		getTareas().add(tarea);
		tarea.setCategoria(this);

		return tarea;
	}

	public Tarea removeTarea(Tarea tarea) {
		getTareas().remove(tarea);
		tarea.setCategoria(null);

		return tarea;
	}

}