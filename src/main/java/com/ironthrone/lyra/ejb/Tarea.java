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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idTarea;

	@Column(name="descripcion_tarea")
	private String descripcionTarea;

	@Column(name="is_active_ta")
	private boolean isActiveTa;

	@Column(name="is_read_ta")
	private boolean isReadTa;

	@Column(name="titulo_tarea")
	private String tituloTarea;

	//bi-directional many-to-one association to Categoria
	@ManyToOne
	private Categoria categoria;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to TareasRol
	@OneToMany(mappedBy="tarea")
	private List<TareasRol> tareasRols;

	public Tarea() {
	}

	public int getIdTarea() {
		return this.idTarea;
	}

	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	public String getDescripcionTarea() {
		return this.descripcionTarea;
	}

	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}

	public boolean getIsActiveTa() {
		return this.isActiveTa;
	}

	public void setIsActiveTa(boolean isActiveTa) {
		this.isActiveTa = isActiveTa;
	}

	public boolean getIsReadTa() {
		return this.isReadTa;
	}

	public void setIsReadTa(boolean isReadTa) {
		this.isReadTa = isReadTa;
	}

	public String getTituloTarea() {
		return this.tituloTarea;
	}

	public void setTituloTarea(String tituloTarea) {
		this.tituloTarea = tituloTarea;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<TareasRol> getTareasRols() {
		return this.tareasRols;
	}

	public void setTareasRols(List<TareasRol> tareasRols) {
		this.tareasRols = tareasRols;
	}

	public TareasRol addTareasRol(TareasRol tareasRol) {
		getTareasRols().add(tareasRol);
		tareasRol.setTarea(this);

		return tareasRol;
	}

	public TareasRol removeTareasRol(TareasRol tareasRol) {
		getTareasRols().remove(tareasRol);
		tareasRol.setTarea(null);

		return tareasRol;
	}

}