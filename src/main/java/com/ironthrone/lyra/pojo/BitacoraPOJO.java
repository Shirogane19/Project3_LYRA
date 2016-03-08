package com.ironthrone.lyra.pojo;

import java.util.Date;


public class BitacoraPOJO {

	private int idBitacora;
	private String accion;
	private Date fecha;
	private int idUsuario;
	private String loginIp;
	private InstitucionPOJO institucion;
	
	public BitacoraPOJO(){
		super();
	}

	public int getIdBitacora() {
		return idBitacora;
	}

	public void setIdBitacora(int idBitacora) {
		this.idBitacora = idBitacora;
	}

	public String getAccion() {
		return accion;
	}

	public void setAccion(String accion) {
		this.accion = accion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public InstitucionPOJO getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionPOJO institucion) {
		this.institucion = institucion;
	}
	
	
}
