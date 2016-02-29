package com.ironthrone.lyra.pojo;

import java.util.List;

import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.MateriasGrado;
import com.ironthrone.lyra.ejb.MateriasProfesor;

public class MateriaPOJO {
	
	private int idMateria;
	private boolean isActiveMat;
	private String nombre;
	private InstitucionPOJO institucion;
	private List<MateriasGradoPOJO> materiasGrados;
	private List<MateriasProfesorPOJO> materiasProfesors;	
	//private Institucion institucion;
	//private List<MateriasGrado> materiasGrados;
	//private List<MateriasProfesor> materiasProfesors;
	
	public MateriaPOJO(){
		super();
	}
	
	public int getIdMateria() {
		return idMateria;
	}
	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}
	public boolean isActiveMat() {
		return isActiveMat;
	}
	public void setActiveMat(boolean isActiveMat) {
		this.isActiveMat = isActiveMat;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
//	public Institucion getInstitucion() {
//		return institucion;
//	}
//	public void setInstitucion(Institucion institucion) {
//		this.institucion = institucion;
//	}
//	public List<MateriasGrado> getMateriasGrados() {
//		return materiasGrados;
//	}
//	public void setMateriasGrados(List<MateriasGrado> materiasGrados) {
//		this.materiasGrados = materiasGrados;
//	}
//	public List<MateriasProfesor> getMateriasProfesors() {
//		return materiasProfesors;
//	}
//	public void setMateriasProfesors(List<MateriasProfesor> materiasProfesors) {
//		this.materiasProfesors = materiasProfesors;
//	}
	
}
