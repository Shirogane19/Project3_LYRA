package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.RolRequest;
import com.ironthrone.lyra.contracts.RolResponse;
import com.ironthrone.lyra.services.RolServiceInterface;


/**
 * Handles requests for the application users.
 * @author jeanpaul
 */
@RestController
@RequestMapping(value ="rest/protected/roles")
public class RolController {
	
	@Autowired private RolServiceInterface rolService;
	
	/**
	 * Retorna una lista de todos los roles del sistema
	 * @param rr
	 * @return Rol Response
	 */
	@RequestMapping(value ="/getAll", method = RequestMethod.POST)
	public RolResponse getAll(@RequestBody RolRequest rr){	
			
		RolResponse rs = new RolResponse();
		rs.setCode(200);
		rs.setCodeMessage("role fetch success");
		rs.setRoles(rolService.getAll());
		return rs;		
	}
	

}
