package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.TareaRequest;
import com.ironthrone.lyra.contracts.TareaResponse;
import com.ironthrone.lyra.services.TareaServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/tarea")

public class TareaController {
	
@Autowired private TareaServiceInterface tareaService;
	
	/**
	 * Retorna una lista de todas las tareas
	 * @param ur
	 * @return tarea Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public TareaResponse getAll(@RequestBody TareaRequest ur){	
			
		TareaResponse tars = new TareaResponse();
		tars.setCode(200);
		tars.setCodeMessage("Seccion fetch success");
		tars.setTareas(tareaService.getAll(ur));
		return tars;		
	}
	
	/**
	 * Retorna una lista de todas las tareas tanto activos o inactivos
	 * @param isActive
	 * @return tarea Response
	 */
	@RequestMapping(value ="/getByState", method = RequestMethod.POST)
	public TareaResponse getByState(@RequestBody boolean isActive){	
			
		TareaResponse tars = new TareaResponse();
		tars.setCode(200);
		tars.setCodeMessage("Tareas fetch success");
		
		if(isActive){
			tars.setTareas(tareaService.getActiveTareas());
		}else{
			tars.setTareas(tareaService.getInactiveTareas());
		}
	
		return tars;		
	}
	
	/**
	 * Retorna una tarea dependiendo de ID
	 * @param idTarea
	 * @return Tarea Response
	 */
	@RequestMapping(value ="/getTarea", method = RequestMethod.POST)
	public TareaResponse getById(@RequestBody int idTarea){	
			
		TareaResponse tars = new TareaResponse();
		tars.setCode(200);
		tars.setCodeMessage("Tarea fetch success");
		tars.setTarea(tareaService.getTareaById(idTarea));
		return tars;		
	}
	
	/**
	 * Guarda los datos de una tarea, remplaza el Create y Update en el CRUD.
	 * @param ur
	 * @return
	 */
	@RequestMapping(value ="/saveTarea", method = RequestMethod.POST)
	public TareaResponse create(@RequestBody TareaRequest ur){	
		
		TareaResponse ta = new TareaResponse();
		Boolean state = tareaService.saveTarea(ur);
		if(state){
			ta.setCode(200);
			ta.setCodeMessage("Tarea saved succesfully");
		}
		return ta;
		
	}
	
	/**
	 * Guarda los datos de una tarea, remplaza el Create y Update en el CRUD.
	 * @param ur
	 * @return
	 */
	@RequestMapping(value ="/getByUser", method = RequestMethod.POST)
	public TareaResponse getByUser(@RequestBody TareaRequest ur){	
		
		int idUsuario = ur.getTarea().getIdUsuario();
		
		TareaResponse ta = new TareaResponse();
		ta.setCode(200);
		ta.setCodeMessage("Tarea fetch success");
		ta.setTareas(tareaService.getTareaByUsuario(idUsuario));
		
		return ta;
		
	}
	
	

}
