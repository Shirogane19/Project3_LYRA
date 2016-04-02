package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.MateriaPOJO;

public class MateriaRequest extends BaseResponse{
	
	private MateriaPOJO materia;

	public MateriaPOJO getMateria() {
		return materia;
	}

	public void setMateria(MateriaPOJO materia) {
		this.materia = materia;
	}
	
	@Override
	public String toString() {
		return "Request de Materia [user=" + materia + "]";
	}

}
