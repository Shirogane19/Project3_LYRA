package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.InstitucionPOJO;

public class InstitucionRequest extends BaseRequest{
	
	private InstitucionPOJO institucion;
	
	public InstitucionRequest(){
		super();
	}

	public InstitucionPOJO getInstitucion() {
		return institucion;
	}

	public void setInstitucion(InstitucionPOJO institucion) {
		this.institucion = institucion;
	}
	
	@Override
	public String toString() {
		return "UsersRequest [institucion=" + institucion + "]";
	}

}
