package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.GradoPOJO;



public class GradoResponse extends BaseResponse{

	private List<GradoPOJO> grados;
	private GradoPOJO grado;
	
	public GradoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<GradoPOJO> getGrados() {
		return grados;
	}
	public void setGrados(List<GradoPOJO> grados) {
		this.grados = grados;
	}
	public GradoPOJO getGrado() {
		return grado;
	}
	public void setGrado(GradoPOJO grado) {
		this.grado = grado;
	}
	
	
}
