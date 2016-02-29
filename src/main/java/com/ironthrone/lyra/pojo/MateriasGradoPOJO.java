package com.ironthrone.lyra.pojo;

public class MateriasGradoPOJO {
	
	private int idMaterias_Grado;
	private GradoPOJO grado;
	private MateriaPOJO materia;
	
	public MateriasGradoPOJO(){
		super();
	}

	public int getIdMaterias_Grado() {
		return idMaterias_Grado;
	}

	public void setIdMaterias_Grado(int idMaterias_Grado) {
		this.idMaterias_Grado = idMaterias_Grado;
	}

	public GradoPOJO getGrado() {
		return grado;
	}

	public void setGrado(GradoPOJO grado) {
		this.grado = grado;
	}

	public MateriaPOJO getMateria() {
		return materia;
	}

	public void setMateria(MateriaPOJO materia) {
		this.materia = materia;
	}
	
	

}
