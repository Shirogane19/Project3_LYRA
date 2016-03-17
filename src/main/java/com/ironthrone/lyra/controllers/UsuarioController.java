package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.ironthrone.lyra.contracts.UsuarioRequest;
import com.ironthrone.lyra.contracts.UsuarioResponse;
import com.ironthrone.lyra.services.UsuarioServiceInterface;



/**
 * Handles requests for the application users.
 * @author jeanpaul
 */
@RestController
@RequestMapping(value ="rest/protected/users")
public class UsuarioController {

	@Autowired private UsuarioServiceInterface usersService;
	
	/**
	 * Retorna una lista de todos los usuarios del sistema
	 * @param ur
	 * @return Usuario Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public UsuarioResponse getAll(@RequestBody UsuarioRequest ur){	
			
		UsuarioResponse us = new UsuarioResponse();
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setUsuarios(usersService.getAll(ur));
		return us;		
	}
	
	/**
	 * Retorna una lista de todos los usuarios activos o inactivos
	 * @param isActive
	 * @return Usuario Response
	 */
	@RequestMapping(value ="/getByState", method = RequestMethod.POST)
	public UsuarioResponse getByState(@RequestBody boolean isActive){	
			
		UsuarioResponse us = new UsuarioResponse();
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		
		if(isActive){
			us.setUsuarios(usersService.getActiveUsers());
		}else{
			us.setUsuarios(usersService.getInactiveUsers());
		}
	
		return us;		
	}
	
	/**
	 * Retorna un unico usuario dado un ID particular.
	 * @param idUsuario
	 * @return Usuario Response
	 */
	@RequestMapping(value ="/getUser", method = RequestMethod.POST)
	public UsuarioResponse getById(@RequestBody UsuarioRequest ur){	
			
		UsuarioResponse us = new UsuarioResponse();
		us.setCode(200);
		us.setCodeMessage("users fetch success");
		us.setUsuario(usersService.getUserById(ur.getUsuario().getIdUsuario()));
		return us;		
	}
	
	/**
	 * Guarda los datos de un usuario, remplaza el Create y Update en el CRUD.
	 * @param ur
	 * @return
	 */
	@RequestMapping(value ="/saveUser", method = RequestMethod.POST)
	public UsuarioResponse create(@RequestBody UsuarioRequest ur){	
		
		System.out.println("Controller:" + ur.toString());
		
		Boolean state = false;
		
		UsuarioResponse us = new UsuarioResponse();
		
		
		if(ur.getUsuario().isAccOwner()){
			state = usersService.editProfile(ur);		
		}else{
			state = usersService.saveUser(ur);			
		}

	
		if(state){
			us.setCode(200);
			us.setCodeMessage("user saved succesfully");
		}
		return us;
		
	}
	
	
	@RequestMapping(value ="/prueba", method = RequestMethod.POST)
	public UsuarioResponse pruebaRoles(){	
		
		UsuarioResponse us = new UsuarioResponse();
		boolean state = usersService.prueba();
		
	
		if(state){
			us.setCode(200);
			us.setCodeMessage("user saved succesfully");
		}
		return us;
		
	}
}
