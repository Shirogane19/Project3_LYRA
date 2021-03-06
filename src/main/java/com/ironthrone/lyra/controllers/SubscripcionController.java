package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ironthrone.lyra.contracts.SubscripcionRequest;
import com.ironthrone.lyra.contracts.SubscripcionResponse;
import com.ironthrone.lyra.services.SubscripcionServiceInterface;

/**
 * Clase de tipo controlador para las subscripciones, recibe las solicitudes y se los envia a 
 * las clase de tipo servicio correspondiente 
 * @author Randall
 *
 */
@RestController
@RequestMapping(value ="rest/protected/subscripcion")
public class SubscripcionController {
	
	@Autowired private SubscripcionServiceInterface SSI;
	
	/**
	 * Retorna una lista de todos los subscripcion del sistema
	 * @return Subscripcion Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public SubscripcionResponse getALL(){
		SubscripcionResponse response = new SubscripcionResponse();
		response.setCode(200);
		response.setCodeMessage("Subscripcion fetch success");
		response.setSubscripciones(SSI.getAll());
		return response;
	}
	
	/**
	 * Guarda los datos de una Subscripcion, remplaza el Create y Update en el CRUD.
	 * @param subscripcionRequest
	 * @return SubscripcionResponse
	 */
	@RequestMapping(value ="/save", method = RequestMethod.POST)
	public SubscripcionResponse create(@RequestBody SubscripcionRequest subscripcionRequest){	
		
		SubscripcionResponse subscripcionRes = new SubscripcionResponse();
		Boolean state = SSI.saveSubscripcion(subscripcionRequest);
	
		if(state){
			subscripcionRes.setCode(200);
			subscripcionRes.setCodeMessage("Subscripcion saved succesfully");
		}
		return subscripcionRes;
		
	}
	
	/**
	 * Retorna uns única Alumno dado un ID particular.
	 * @param idAlumno
	 * @return AlumnoResponse
	 */
	@RequestMapping(value ="/getSubscripcion", method = RequestMethod.POST)
	public SubscripcionResponse getById(@RequestBody int idSubscripcion){	
			
		SubscripcionResponse subscripcionResponse = new SubscripcionResponse();
		subscripcionResponse.setCode(200);
		subscripcionResponse.setCodeMessage("Subscripcion fetch success");
		subscripcionResponse.setSubscripcion(SSI.getSubscripcionById(idSubscripcion));
		return subscripcionResponse;		
	}
	
	/**
	 * Retorna una lista de todos los subscripcion del sistema
	 * @return Subscripcion Response
	 */
	@RequestMapping(value ="/getAllActive", method = RequestMethod.POST)
	public SubscripcionResponse getALLActive(){
		SubscripcionResponse response = new SubscripcionResponse();
		response.setCode(200);
		response.setCodeMessage("Subscripcion fetch success");
		response.setSubscripciones(SSI.getAllByActive());
		return response;
	}
	
	@RequestMapping(value ="/pruebaFechaComparacion", method = RequestMethod.POST)
	public SubscripcionResponse pruebaFechaComparacion(){
		SubscripcionResponse response = new SubscripcionResponse();
		response.setCode(200);
		response.setCodeMessage("Subscripcion fetch success");
		SSI.revisarVencimientos();
		return response;
	}
	
	@RequestMapping(value ="/renovarSubscripcion", method = RequestMethod.POST)
	public SubscripcionResponse renovarSubscripcion(@RequestBody SubscripcionRequest subscripcionRequest){
		SubscripcionResponse response = new SubscripcionResponse();
		response.setCode(200);
		response.setCodeMessage("Subscripcion fetch success");
		response.setSubscripcion(SSI.renovarSubscripcion(subscripcionRequest));
		return response;
	}
	

}
