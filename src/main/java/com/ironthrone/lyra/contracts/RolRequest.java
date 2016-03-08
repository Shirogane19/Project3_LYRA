package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.RolPOJO;

public class RolRequest extends BaseRequest{
	
	private RolPOJO rol;

	public RolPOJO getRol() {
		return rol;
	}

	public void setRol(RolPOJO rol) {
		this.rol = rol;
	}
	
	@Override
	public String toString() {
		return "Request de Rol [rol=" + rol + "]";
	}

}
