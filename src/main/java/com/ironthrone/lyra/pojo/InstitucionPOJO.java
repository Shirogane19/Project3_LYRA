package com.ironthrone.lyra.pojo;

import java.util.List;

import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.ejb.Bitacora;
import com.ironthrone.lyra.ejb.Grado;
import com.ironthrone.lyra.ejb.Materia;
import com.ironthrone.lyra.ejb.Subscripcion;
import com.ironthrone.lyra.ejb.Usuario;

public class InstitucionPOJO {
	
	private int idInstitucion;
	private boolean hasSuscripcion;
	private String logoInstitucion;
	private String nombreInstitucion;
	private List<AlumnoPOJO> alumnos;
	private List<BitacoraPOJO> bitacoras;
	private List<GradoPOJO> grados;
	private List<MateriaPOJO> materias;
	private List<SubscripcionPOJO> subscripcions;
	private List<UsuarioPOJO> usuarios;
	
	public InstitucionPOJO(){
		super();
	}

	public int getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(int idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	public boolean isHasSuscripcion() {
		return hasSuscripcion;
	}

	public void setHasSuscripcion(boolean hasSuscripcion) {
		this.hasSuscripcion = hasSuscripcion;
	}

	public String getLogoInstitucion() {
		return logoInstitucion;
	}

	public void setLogoInstitucion(String logoInstitucion) {
		this.logoInstitucion = logoInstitucion;
	}

	public String getNombreInstitucion() {
		return nombreInstitucion;
	}

	public void setNombreInstitucion(String nombreInstitucion) {
		this.nombreInstitucion = nombreInstitucion;
	}

	public List<AlumnoPOJO> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnoPOJO> alumnos) {
		this.alumnos = alumnos;
	}

	public List<BitacoraPOJO> getBitacoras() {
		return bitacoras;
	}

	public void setBitacoras(List<BitacoraPOJO> bitacoras) {
		this.bitacoras = bitacoras;
	}

	public List<GradoPOJO> getGrados() {
		return grados;
	}

	public void setGrados(List<GradoPOJO> grados) {
		this.grados = grados;
	}

	public List<MateriaPOJO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaPOJO> materias) {
		this.materias = materias;
	}

	public List<SubscripcionPOJO> getSubscripcions() {
		return subscripcions;
	}

	public void setSubscripcions(List<SubscripcionPOJO> subscripcions) {
		this.subscripcions = subscripcions;
	}

	public List<UsuarioPOJO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioPOJO> usuarios) {
		this.usuarios = usuarios;
	}
	
	

}
