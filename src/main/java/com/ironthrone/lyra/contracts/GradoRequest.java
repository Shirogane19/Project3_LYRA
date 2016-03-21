package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.GradoPOJO;

public class GradoRequest extends BaseRequest{


	private GradoPOJO grado	;
	
	public GradoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public GradoPOJO getGrado() {
		return grado;
	}
	public void setGrado(GradoPOJO grado) {
		this.grado = grado;
	}
	
}
