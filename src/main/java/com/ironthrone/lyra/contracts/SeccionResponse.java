package com.ironthrone.lyra.contracts;
import java.util.List;

import com.ironthrone.lyra.pojo.SeccionPOJO;

public class SeccionResponse extends BaseResponse{
	
	private List<SeccionPOJO> secciones;
	private SeccionPOJO seccion;
	
	public SeccionResponse(){
		super();
	}
	
	public List<SeccionPOJO> getSecciones() {
		return secciones;
	}

	public void setSecciones(List<SeccionPOJO> secciones) {
		this.secciones = secciones;
	}

	public SeccionPOJO getSeccion() {
		return seccion;
	}

	public void setSeccion(SeccionPOJO seccion) {
		this.seccion = seccion;
	}
	
	
	
 
}
