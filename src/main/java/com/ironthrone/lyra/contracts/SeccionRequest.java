package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.SeccionPOJO;

public class SeccionRequest {
	private SeccionPOJO seccion;

	public SeccionPOJO getSeccion() {
		return seccion;
	}

	public void setSeccion(SeccionPOJO seccion) {
		this.seccion = seccion;
	}
	
	@Override
	public String toString() {
		return "Request de Sección [user=" + seccion + "]";
	}
}
