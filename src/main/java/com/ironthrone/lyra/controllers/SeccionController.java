package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.SeccionRequest;
import com.ironthrone.lyra.contracts.SeccionResponse;
import com.ironthrone.lyra.services.SeccionServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/seccion")

public class SeccionController {
	
	@Autowired private SeccionServiceInterface seccionService;
	
	/**
	 * Retorna una lista de todas las secciones
	 * @param ur
	 * @return Seccion Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public SeccionResponse getAll(@RequestBody SeccionRequest ur){	
			
		SeccionResponse secs = new SeccionResponse();
		secs.setCode(200);
		secs.setCodeMessage("Seccion fetch success");
		secs.setSecciones(seccionService.getAll(ur));
		return secs;		
	}
	
	/**
	 * Retorna una lista de todas las secciones tanto activos o inactivos
	 * @param isActive
	 * @return Seccion Response
	 */
	@RequestMapping(value ="/getByState", method = RequestMethod.POST)
	public SeccionResponse getByState(@RequestBody boolean isActive){	
			
		SeccionResponse secs = new SeccionResponse();
		secs.setCode(200);
		secs.setCodeMessage("Secciones fetch success");
		
		if(isActive){
			secs.setSecciones(seccionService.getActiveSecciones());
		}else{
			secs.setSecciones(seccionService.getInactiveSecciones());
		}
	
		return secs;		
	}
	
	/**
	 * Retorna una seccion dependiendo de ID
	 * @param idSeccion
	 * @return Seccion Response
	 */
	@RequestMapping(value ="/getSeccion", method = RequestMethod.POST)
	public SeccionResponse getById(@RequestBody int idSeccion){	
			
		SeccionResponse secs = new SeccionResponse();
		secs.setCode(200);
		secs.setCodeMessage("Seccion fetch success");
		secs.setSeccion(seccionService.getSeccionById(idSeccion));
		return secs;		
	}
	/**
	 * Guarda los datos de una seccion, remplaza el Create y Update en el CRUD.
	 * @param ur
	 * @return
	 */
	@RequestMapping(value ="/saveSeccion", method = RequestMethod.POST)
	public SeccionResponse create(@RequestBody SeccionRequest ur){	
		
		SeccionResponse sec = new SeccionResponse();
		Boolean state = seccionService.saveSeccion(ur);
		if(state){
			sec.setCode(200);
			sec.setCodeMessage("Sección saved succesfully");
		}
		return sec;
		
	}

}
