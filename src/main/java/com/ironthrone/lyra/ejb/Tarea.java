package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tarea database table.
 * 
 */
@Entity
@NamedQuery(name="Tarea.findAll", query="SELECT t FROM Tarea t")
public class Tarea implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idTarea;
	private String descripcionTarea;
	private boolean isActiveTa;
	private boolean isReadTa;
	private String tituloTarea;
	private List<Rol> rols;
	private Categoria categoria;
	private List<Usuario> usuarios;

	public Tarea() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdTarea() {
		return this.idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}


	@Column(name="descripcion_tarea")
	public String getDescripcionTarea() {
		return this.descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}


	@Column(name="is_active_ta")
	public boolean getIsActiveTa() {
		return this.isActiveTa;
	}

	public void setIsActiveTa(boolean isActiveTa) {
		this.isActiveTa = isActiveTa;
	}


	@Column(name="is_read_ta")
	public boolean getIsReadTa() {
		return this.isReadTa;
	}

	public void setIsReadTa(boolean isReadTa) {
		this.isReadTa = isReadTa;
	}


	@Column(name="titulo_tarea")
	public String getTituloTarea() {
		return this.tituloTarea;
	}

	public void setTituloTarea(String tituloTarea) {
		this.tituloTarea = tituloTarea;
	}


	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
		name="tareas_rol"
		, joinColumns={
			@JoinColumn(name="Tarea_idTarea")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Rol_idRol")
			}
		)
	public List<Rol> getRols() {
		return this.rols;
	}

	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}


	//bi-directional many-to-one association to Categoria
	@ManyToOne(fetch=FetchType.LAZY)
	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany (fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="tareas_usuario"
		, joinColumns={
			@JoinColumn(name="Tarea_idTarea")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Usuario_idUsuario")
			}
		)
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}