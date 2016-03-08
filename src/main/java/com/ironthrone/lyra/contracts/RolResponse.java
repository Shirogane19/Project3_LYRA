package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.RolPOJO;

public class RolResponse extends BaseResponse {
	
	private List<RolPOJO> roles;
	private RolPOJO rol;
	
	public RolResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<RolPOJO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolPOJO> roles) {
		this.roles = roles;
	}

	public RolPOJO getRol() {
		return rol;
	}

	public void setRol(RolPOJO rol) {
		this.rol = rol;
	}
	
	
	

}
