package com.ironthrone.lyra.pojo;

import java.util.Date;
import java.util.List;

public class TareaPOJO {
	private int idTarea;
	private String descripcionTarea;
	private boolean isActiveTa;
	private boolean isReadTa;
	private String tituloTarea;
	private List<RolPOJO> rols;
	private CategoriaPOJO categoria;
	private List<UsuarioPOJO> usuarios;
	private List<String> idRols;
	private List<String> idUsuarios;
	private int idCategoria;
	private int idOwner;
	private Date dateOfReport;

	
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
	public List<RolPOJO> getRols() {
		return rols;
	}
	public void setRols(List<RolPOJO> list) {
		this.rols = list;
	}
	public CategoriaPOJO getCategoria() {
		return categoria;
	}
	public void setCategoria(CategoriaPOJO categoriaPOJO) {
		this.categoria = categoriaPOJO;
	}
	public List<UsuarioPOJO> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<UsuarioPOJO> list) {
		this.usuarios = list;
	}
	public int getIdCategoria() {
		return idCategoria;
	}
	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public int getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(int idOwner) {
		this.idOwner = idOwner;
	}

	public Date getDateOfReport() {
		return dateOfReport;
	}

	public void setDateOfReport(Date dateOfReport) {
		this.dateOfReport = dateOfReport;
	}
	
	
	
	
	
	
}
