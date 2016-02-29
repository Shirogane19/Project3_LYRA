package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.MateriaPOJO;;

public class MateriaResponse extends BaseResponse{

	private List<MateriaPOJO> materias;
	private MateriaPOJO materia;
	
	public MateriaResponse(){
		super();
	}

	public List<MateriaPOJO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaPOJO> materias) {
		this.materias = materias;
	}

	public MateriaPOJO getMateria() {
		return materia;
	}

	public void setMateria(MateriaPOJO materia) {
		this.materia = materia;
	}
	
	
	
	
}
