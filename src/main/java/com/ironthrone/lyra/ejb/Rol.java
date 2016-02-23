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
	private List<RolUsuario> rolUsuarios;
	private List<TareasRol> tareasRols;

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


	//bi-directional many-to-one association to RolUsuario
	@OneToMany(mappedBy="rol")
	public List<RolUsuario> getRolUsuarios() {
		return this.rolUsuarios;
	}

	public void setRolUsuarios(List<RolUsuario> rolUsuarios) {
		this.rolUsuarios = rolUsuarios;
	}

	public RolUsuario addRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().add(rolUsuario);
		rolUsuario.setRol(this);

		return rolUsuario;
	}

	public RolUsuario removeRolUsuario(RolUsuario rolUsuario) {
		getRolUsuarios().remove(rolUsuario);
		rolUsuario.setRol(null);

		return rolUsuario;
	}


	//bi-directional many-to-one association to TareasRol
	@OneToMany(mappedBy="rol")
	public List<TareasRol> getTareasRols() {
		return this.tareasRols;
	}

	public void setTareasRols(List<TareasRol> tareasRols) {
		this.tareasRols = tareasRols;
	}

	public TareasRol addTareasRol(TareasRol tareasRol) {
		getTareasRols().add(tareasRol);
		tareasRol.setRol(this);

		return tareasRol;
	}

	public TareasRol removeTareasRol(TareasRol tareasRol) {
		getTareasRols().remove(tareasRol);
		tareasRol.setRol(null);

		return tareasRol;
	}

}