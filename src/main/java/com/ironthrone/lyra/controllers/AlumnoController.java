package com.ironthrone.lyra.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.AlumnoRequest;
import com.ironthrone.lyra.contracts.AlumnoResponse;
import com.ironthrone.lyra.services.AlumnoServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/alumno")
/**
 * Clase de tipo controlador para los alumnos, recibe las solicitudes y se los envia a 
 * las clase de tipo servicio correspondiente
 * @author Randall
 *
 */
public class AlumnoController {
	
	@Autowired private AlumnoServiceInterface ASI;
	@Autowired private HttpServletRequest request;
	
	/**
	 * Retorna una lista de todos los alumnos del sistema
	 * @return Alumno Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public AlumnoResponse getALL(){
		AlumnoResponse response = new AlumnoResponse();
		response.setCode(200);
		response.setCodeMessage("Alumno fetch success");
		response.setAlumnos(ASI.getAll());
		return response;
	}
	
	/**
	 * Guarda los datos de un Alumno, remplaza el Create y Update en el CRUD.
	 * @param alumnoRequest
	 * @return AlumnoResponse
	 */
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public AlumnoResponse create(@RequestBody AlumnoRequest alumnoRequest){	
		
		AlumnoResponse alumnoRes = new AlumnoResponse();
		Boolean state = ASI.saveAlumno(alumnoRequest);
	
		if(state){
			alumnoRes.setCode(200);
			alumnoRes.setCodeMessage("Alumno saved succesfully");
		}
		return alumnoRes;
		
	}
	
	/**
	 * Retorna un único Alumno dado un ID particular.
	 * @param idAlumno
	 * @return AlumnoResponse
	 */
	@RequestMapping(value ="/getAlumno", method = RequestMethod.POST)
	public AlumnoResponse getById(@RequestBody int idAlumno){	
			
		AlumnoResponse alumnoResponse = new AlumnoResponse();
		alumnoResponse.setCode(200);
		alumnoResponse.setCodeMessage("users fetch success");
		alumnoResponse.setAlumno(ASI.getAlumnoById(idAlumno));
		return alumnoResponse;		
	}
	
}
