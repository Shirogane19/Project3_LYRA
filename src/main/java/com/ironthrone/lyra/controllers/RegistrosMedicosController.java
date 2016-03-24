package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.RegistroMedicoRequest;
import com.ironthrone.lyra.contracts.RegistroMedicoResponse;
import com.ironthrone.lyra.services.RegistroMedicoServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/historialMedico")
/**
 * Clase de tipo controlador para los registros medicos de los alumnos, recibe las solicitudes y se los envia a 
 * las clase de tipo servicio correspondiente
 * @author Jean
 *
 */
public class RegistrosMedicosController {
	
	@Autowired RegistroMedicoServiceInterface historialService;
	
	/**
	 * Retorna un único historial medico de un alumno dado su ID particular.
	 * @param idAlumno
	 * @return RegistroMedicoResponse
	 */
	
	@RequestMapping(value ="/getHistorialMedico", method = RequestMethod.POST)
	public RegistroMedicoResponse getHistorialMedico(@RequestBody RegistroMedicoRequest histReq){	
			
		RegistroMedicoResponse response = new RegistroMedicoResponse();
		response.setCode(200);
		response.setCodeMessage("student record fetch success");
		response.setRegistros(historialService.getAll(histReq));
		
		return response;		
	}

	/**
	 * Guarda una nueva entrada en el historial medico de un Alumno.
	 * @param idAlumno
	 * @return RegistroMedicoResponse
	 */
	
	@RequestMapping(value ="/saveRegistroMedico", method = RequestMethod.POST)
	public RegistroMedicoResponse saveRegistro(@RequestBody RegistroMedicoRequest histReq){	
		
		RegistroMedicoResponse response = new RegistroMedicoResponse();
		Boolean state = historialService.saveRegistro(histReq);
	
		if(state){
			response.setCode(200);
			response.setCodeMessage("student record save success");
		}
		
		return response;		
	}
	
	/**
	 * Borra una entrada en el historial medico de un Alumno.
	 * @param idAlumno
	 * @return RegistroMedicoResponse
	 */
	
	@RequestMapping(value ="/deleteRegistroMedico", method = RequestMethod.POST)
	public RegistroMedicoResponse deleteRegistro(@RequestBody RegistroMedicoRequest histReq){	
		
		RegistroMedicoResponse response = new RegistroMedicoResponse();
		Boolean state = historialService.deleteRegistro(histReq);
	
		if(!state){
			response.setCode(200);
			response.setCodeMessage("student record deleted success");
		}
		
		return response;		
	}
	

}
