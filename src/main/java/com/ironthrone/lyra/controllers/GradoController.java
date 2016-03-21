package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.GradoRequest;
import com.ironthrone.lyra.contracts.GradoResponse;
import com.ironthrone.lyra.services.GradoServiceInterface;


/**
 * Handles requests for the application users.
 * @author jeanpaul
 */
@RestController
@RequestMapping(value ="rest/protected/grado")
public class GradoController {
	
	@Autowired GradoServiceInterface gradeService;

	/**
	 * Retorna una lista de todos los gradoss del sistema
	 * @param gr
	 * @return Usuario Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public GradoResponse getAll(@RequestBody GradoRequest gr){	
			
		GradoResponse gs = new GradoResponse();
		gs.setCode(200);
		gs.setCodeMessage("grades fetch success");
		gs.setGrados(gradeService.getAll());
		return gs;		
	}
	
	/**
	 * Retorna un unico grado dado un ID particular.
	 * @param idGrado
	 * @return Grado Response
	 */
	@RequestMapping(value ="/getGrade", method = RequestMethod.POST)
	public GradoResponse getById(@RequestBody GradoRequest gr){	
			
		GradoResponse gs = new GradoResponse();
		gs.setCode(200);
		gs.setCodeMessage("grade fetch success");
		gs.setGrado(gradeService.getGradoById(gr.getGrado().getIdGrado()));
		return gs;		
	}
	
	/**
	 * Guarda los datos de un frado, remplaza el Create y Update en el CRUD.
	 * @param gr
	 * @return
	 */
	
	@RequestMapping(value ="/saveGrade", method = RequestMethod.POST)
	public GradoResponse create(@RequestBody GradoRequest gr){	
		
		System.out.println("Controller:" + gr.toString());
		GradoResponse gs = new GradoResponse();
	
		Boolean state = gradeService.saveGrado(gr);			
	
		if(state){
			gs.setCode(200);
			gs.setCodeMessage("grade saved succesfully");
		}
		return gs;
		
	}
	
}
