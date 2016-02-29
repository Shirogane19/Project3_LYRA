package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.AlumnoPOJO;

public class AlumnoRequest extends BaseRequest {
	
	private AlumnoPOJO alumno;
	
	public AlumnoRequest(){
		super();
	}

	public AlumnoPOJO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoPOJO alumno) {
		this.alumno = alumno;
	}

	@Override
	public String toString() {
		return "UsersRequest [alumno=" + alumno + "]";
	}
}
