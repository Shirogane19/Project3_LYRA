package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.AlumnoRequest;
import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.pojo.AlumnoPOJO;

public interface AlumnoServiceInterface {

	List<AlumnoPOJO> getAll();
	Boolean saveAlumno(AlumnoRequest alumnoRequest);
	AlumnoPOJO getAlumnoById(int idAlumno);
	Alumno findById(int idAlumno);
}
