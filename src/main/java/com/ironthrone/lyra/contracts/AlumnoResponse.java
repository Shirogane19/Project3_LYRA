package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.AlumnoPOJO;

/**
 * Clase de tipo response, retorna un contrato con los alumnos solicitados
 * @author Randall
 *
 */
public class AlumnoResponse extends BaseResponse{
	
	private List<AlumnoPOJO> alumnos;
	private AlumnoPOJO alumno;
	
	public AlumnoResponse(){
		super();
	}

	public List<AlumnoPOJO> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnoPOJO> alumnos) {
		this.alumnos = alumnos;
	}

	public AlumnoPOJO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoPOJO alumno) {
		this.alumno = alumno;
	}
	
	
	
	

}
