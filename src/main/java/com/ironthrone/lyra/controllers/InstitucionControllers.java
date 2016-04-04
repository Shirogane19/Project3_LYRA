package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ironthrone.lyra.contracts.InstitucionRequest;
import com.ironthrone.lyra.contracts.InstitucionResponse;
import com.ironthrone.lyra.services.InstitucionServiceInterface;

/**
 * Clase de tipo controlador para las instituciones, recibe las solicitudes y se los envia a 
 * las clase de tipo servicio correspondiente
 * @author Randall
 *
 */
@RestController
@RequestMapping(value ="rest/protected/institucion")
public class InstitucionControllers {
	
	@Autowired private InstitucionServiceInterface ISI;

	
	/**
	 * Retorna una lista de todas las instituciones del sistema
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public InstitucionResponse getALL(@RequestBody InstitucionRequest ir){
		
		InstitucionResponse response = new InstitucionResponse();
		response.setCode(200);
		response.setCodeMessage("Institutions fetch success");
		response.setInstituciones(ISI.getAll());
		return response;
	}
	
	/**
	 * Guarda los datos de una institucion, remplaza el Create y Update en el CRUD.
	 * @param institucionRequest
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public InstitucionResponse create(@RequestBody InstitucionRequest institucionRequest){	
		
		InstitucionResponse institucionRes = new InstitucionResponse();
		Boolean state = ISI.saveInstitucion(institucionRequest);
	
		if(state){
			institucionRes.setCode(200);
			institucionRes.setCodeMessage("Institucion saved succesfully");
		}
		return institucionRes;
		
	}
	
	/**
	 * Retorna una única institución dado un ID particular.
	 * @param idInstitucion
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getInstituto", method = RequestMethod.POST)
	public InstitucionResponse getById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getInstitucionById(idInstitucion));
		return institucionResponse;		
	}
	
	/**
	 * Retorna una única institución dado un ID particular con sus alumnos.
	 * @param Integer
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getAlumnosDelInstituto", method = RequestMethod.POST)
	public InstitucionResponse getAlumnosDeInstitucionById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getAlumnosDeInstitucionById(idInstitucion));
		return institucionResponse;		
	}
	
	/**
	 * Retorna una única institución dado un ID particular con sus usuarios.
	 * @param Integer
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getUsuariosDelInstituto", method = RequestMethod.POST)
	public InstitucionResponse getUsuariosDeInstitucionById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getUsuariosDeInstitucionById(idInstitucion));
		return institucionResponse;		
	}
	
	/**
	 * Retorna una única institución dado un ID particular con sus usuarios.
	 * @param Integer
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getGradosDelInstituto", method = RequestMethod.POST)
	public InstitucionResponse getGradosDeInstitucionById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getGradosDeInstitucionById(idInstitucion));
		return institucionResponse;		
	}
	
	/**
	 * Retorna una única institución dado un ID particular con sus alumnos sin secciones.
	 * @param Integer
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getAlumnosSinSeccionDelInstituto", method = RequestMethod.POST)
	public InstitucionResponse getAlumnosSinSeccionDeInstitucionById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getAlumnosSinSeccion(idInstitucion));
		return institucionResponse;		
	}
	
	/**
	 * Retorna una única institución dado un ID particular con sus alumnos sin secciones.
	 * @param Integer
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getMateriasDelInstituto", method = RequestMethod.POST)
	public InstitucionResponse getMateriasDeInstitucionById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getMateriasDeInstitucionById(idInstitucion));
		return institucionResponse;		
	}

	/**
	 * Retorna las subscripciones de una institución
	 * @param Integer
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getSubscripcionesDelInstituto", method = RequestMethod.POST)
	public InstitucionResponse getSubscripcionesDeInstitucionById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getSubscripciones(idInstitucion));
		return institucionResponse;		
	}
	
	/**
	 * Retorna los profesores de una institución
	 * @param Integer
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getProfesoresDelInstituto", method = RequestMethod.POST)
	public InstitucionResponse getProfesoresDeInstitucionById(@RequestBody int idInstitucion){	
			
		InstitucionResponse institucionResponse = new InstitucionResponse();
		institucionResponse.setCode(200);
		institucionResponse.setCodeMessage("users fetch success");
		institucionResponse.setInstitucion(ISI.getProfesoresDelInstituto(idInstitucion));
		return institucionResponse;		
	}
}
