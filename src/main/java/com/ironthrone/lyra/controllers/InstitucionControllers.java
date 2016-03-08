package com.ironthrone.lyra.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.AlumnoRequest;
import com.ironthrone.lyra.contracts.AlumnoResponse;
import com.ironthrone.lyra.contracts.InstitucionRequest;
import com.ironthrone.lyra.contracts.InstitucionResponse;
import com.ironthrone.lyra.services.InstitucionServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/institucion")
public class InstitucionControllers {
	
	@Autowired private InstitucionServiceInterface ISI;
	@Autowired private HttpServletRequest request;
	
	/**
	 * Retorna una lista de todas las instituciones del sistema
	 * @return InstitucionResponse
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public InstitucionResponse getALL(){
		InstitucionResponse response = new InstitucionResponse();
		response.setCode(200);
		response.setCodeMessage("Institucion fetch success");
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

}
