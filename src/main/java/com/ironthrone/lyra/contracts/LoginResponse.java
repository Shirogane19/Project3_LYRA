package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.pojo.InstitucionPOJO;


public class LoginResponse extends BaseResponse {

	private int userId;
	private List<Integer> idInstitucions;
	private List<InstitucionPOJO> instituciones;
	private List<Integer> idRoles;
	private int idInstitucion;
	private String firstName;
	private String lastName; 

	public LoginResponse() {
		super();
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(int idInstitucion) {
		this.idInstitucion = idInstitucion;
	}
	
	public List<Integer> getIdInstitucions() {
		return idInstitucions;
	}

	public void setIdInstitucions(List<Integer> idInstitucions) {
		this.idInstitucions = idInstitucions;
	}

	public List<Integer> getIdRoles() {
		return idRoles;
	}

	public void setIdRoles(List<Integer> idRoles) {
		this.idRoles = idRoles;
	}

	public List<InstitucionPOJO> getInstituciones() {
		return instituciones;
	}

	public void setInstituciones(List<InstitucionPOJO> list) {
		this.instituciones = list;
	}
	
	


}