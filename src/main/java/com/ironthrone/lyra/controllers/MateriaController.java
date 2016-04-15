package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.MateriaRequest;
import com.ironthrone.lyra.contracts.MateriaResponse;
import com.ironthrone.lyra.services.MateriaServiceInterface;


@RestController
@RequestMapping(value ="rest/protected/materia")

public class MateriaController {
	@Autowired private MateriaServiceInterface materiaService;
	
	
	/**
	 * Retorna una lista de todas las materias
	 * @param ur
	 * @return Materia Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public MateriaResponse getAll(@RequestBody MateriaRequest ur){	
			
		MateriaResponse mats = new MateriaResponse();
		mats.setCode(200);
		mats.setCodeMessage("Materia fetch success");
		mats.setMaterias(materiaService.getAll(ur));
		return mats;		
	}
	
	/**
	 * Retorna una lista de todas las materias tanto activos o inactivos
	 * @param isActive
	 * @return Materia Response
	 */
	@RequestMapping(value ="/getByState", method = RequestMethod.POST)
	public MateriaResponse getByState(@RequestBody boolean isActive){	
			
		MateriaResponse mats = new MateriaResponse();
		mats.setCode(200);
		mats.setCodeMessage("Materias fetch success");
		
		if(isActive){
			mats.setMaterias(materiaService.getActiveMaterias());
		}else{
			mats.setMaterias(materiaService.getInactiveMaterias());
		}
	
		return mats;		
	}
	
	/**
	 * Retorna una materia dependiendo de ID
	 * @param idMateria
	 * @return Materia Response
	 */
	@RequestMapping(value ="/getMateria", method = RequestMethod.POST)
	public MateriaResponse getById(@RequestBody int idMateria){	
			
		MateriaResponse mats = new MateriaResponse();
		mats.setCode(200);
		mats.setCodeMessage("Materia fetch success");
		mats.setMateria(materiaService.getMateriaById(idMateria));
		return mats;		
	}
	
	/**
	 * Guarda los datos de una materia, remplaza el Create y Update en el CRUD.
	 * @param ur
	 * @return
	 */
	@RequestMapping(value ="/saveMateria", method = RequestMethod.POST)
	public MateriaResponse create(@RequestBody MateriaRequest ur){	
		
		System.out.println("Estado: " + ur.getMateria().isActiveMat());
		
		MateriaResponse mat = new MateriaResponse();
		Boolean state = materiaService.saveMateria(ur);
		if(state){
			mat.setCode(200);
			mat.setCodeMessage("user saved succesfully");
		}
		return mat;
		
	}
	
	/**
	 * Retorna una lista de los profesores de una materia
	 * @param idMateria
	 * @return Materia Response
	 */
	@RequestMapping(value ="/getProfesDeMateria", method = RequestMethod.POST)
	public MateriaResponse getProfesores(@RequestBody int idMateria){	
			
		MateriaResponse mats = new MateriaResponse();
		mats.setCode(200);
		mats.setCodeMessage("Materia fetch success");
		mats.setMateria(materiaService.getProfes(idMateria));
		return mats;		
	}
	
	
}
