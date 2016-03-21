package com.ironthrone.lyra.pojo;

import java.util.List;

import com.ironthrone.lyra.ejb.Categoria;
import com.ironthrone.lyra.ejb.Rol;
import com.ironthrone.lyra.ejb.Usuario;

public class TareaPOJO {
	private int idTarea;
	private String descripcionTarea;
	private boolean isActiveTa;
	private boolean isReadTa;
	private String tituloTarea;
	private List<Rol> rols;
	private Categoria categoria;
	private List<Usuario> usuarios;
	private List<String> idRols;
	private List<String> idUsuarios;
	
	
	public TareaPOJO(){
		super();
	}
	
	public List<String> getIdRols() {
		return idRols;
	}
	public void setIdRols(List<String> idRols) {
		this.idRols = idRols;
	}
	public List<String> getIdUsuarios() {
		return idUsuarios;
	}
	public void setIdUsuarios(List<String> idUsuarios) {
		this.idUsuarios = idUsuarios;
	}
	public int getIdTarea() {
		return idTarea;
	}
	public void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}
	public String getDescripcionTarea() {
		return descripcionTarea;
	}
	public void setDescripcionTarea(String descripcionTarea) {
		this.descripcionTarea = descripcionTarea;
	}
	public boolean isActiveTa() {
		return isActiveTa;
	}
	public void setActiveTa(boolean isActiveTa) {
		this.isActiveTa = isActiveTa;
	}
	public boolean isReadTa() {
		return isReadTa;
	}
	public void setReadTa(boolean isReadTa) {
		this.isReadTa = isReadTa;
	}
	public String getTituloTarea() {
		return tituloTarea;
	}
	public void setTituloTarea(String tituloTarea) {
		this.tituloTarea = tituloTarea;
	}
	public List<Rol> getRols() {
		return rols;
	}
	public void setRols(List<Rol> rols) {
		this.rols = rols;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	
}
