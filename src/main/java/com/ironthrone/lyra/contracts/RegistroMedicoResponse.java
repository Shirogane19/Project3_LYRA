package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.RegistroMedicoPOJO;

public class RegistroMedicoResponse extends BaseResponse{

	private RegistroMedicoPOJO registro;
	private List<RegistroMedicoPOJO> registros;
	
	public RegistroMedicoResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RegistroMedicoPOJO getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroMedicoPOJO registro) {
		this.registro = registro;
	}

	public List<RegistroMedicoPOJO> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroMedicoPOJO> registros) {
		this.registros = registros;
	}
		
	
}
