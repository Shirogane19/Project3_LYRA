package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.RegistroMedicoPOJO;

public class RegistroMedicoRequest extends BaseRequest{
	
	private RegistroMedicoPOJO registro;
	private int idAlumno;
	
	
	public RegistroMedicoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RegistroMedicoPOJO getRegistro() {
		return registro;
	}


	public void setRegistro(RegistroMedicoPOJO registro) {
		this.registro = registro;
	}


	public int getIdAlumno() {
		return idAlumno;
	}


	public void setIdAlumno(int idAlumno) {
		this.idAlumno = idAlumno;
	}
	
	
	
	

}
