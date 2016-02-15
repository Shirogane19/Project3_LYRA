package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rol_usuario database table.
 * 
 */
@Entity
@Table(name="rol_usuario")
@NamedQuery(name="RolUsuario.findAll", query="SELECT r FROM RolUsuario r")
public class RolUsuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRol_Usuario;

	//bi-directional many-to-one association to Rol
	@ManyToOne
	private Rol rol;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public RolUsuario() {
	}

	public int getIdRol_Usuario() {
		return this.idRol_Usuario;
	}

	public void setIdRol_Usuario(int idRol_Usuario) {
		this.idRol_Usuario = idRol_Usuario;
	}

	public Rol getRol() {
		return this.rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}