package com.ironthrone.lyra.pojo;

import com.ironthrone.lyra.ejb.Materia;
import com.ironthrone.lyra.ejb.Usuario;

public class MateriasProfesorPOJO {
	
	private int idMaterias_Profesor;
	private MateriaPOJO materia;
	private UsuarioPOJO usuario;
	
	public MateriasProfesorPOJO(){
		super();
	}

	public int getIdMaterias_Profesor() {
		return idMaterias_Profesor;
	}

	public void setIdMaterias_Profesor(int idMaterias_Profesor) {
		this.idMaterias_Profesor = idMaterias_Profesor;
	}

	public MateriaPOJO getMateria() {
		return materia;
	}

	public void setMateria(MateriaPOJO materia) {
		this.materia = materia;
	}

	public UsuarioPOJO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPOJO usuario) {
		this.usuario = usuario;
	}
	
	

}
