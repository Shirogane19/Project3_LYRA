package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.InstitucionPOJO;

public class InstitucionResponse extends BaseResponse{
	
	private List<InstitucionPOJO> instituciones;
	private InstitucionPOJO institucion;
	
	public InstitucionResponse(){
		super();
	}

	public List<InstitucionPOJO> getInstituciones() {
		return instituciones;
	}

	public void setInstituciones(List<InstitucionPOJO> instituciones) {
		this.instituciones = instituciones;
	}

	public InstitucionPOJO getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionPOJO institucion) {
		this.institucion = institucion;
	}
	
	
	

}
