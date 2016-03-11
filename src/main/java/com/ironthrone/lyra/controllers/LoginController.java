package com.ironthrone.lyra.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.BaseResponse;
import com.ironthrone.lyra.contracts.LoginRequest;
import com.ironthrone.lyra.contracts.LoginResponse;
import com.ironthrone.lyra.services.LoginServiceInterface;



/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "rest/login")
public class LoginController {
	
	@Autowired private LoginServiceInterface loginService;
	
	@RequestMapping(value = "/checkuser", method = RequestMethod.POST)
	@Transactional
	public BaseResponse checkuser(@RequestBody LoginRequest lr,HttpServletRequest servletRequest,HttpServletResponse servletResponse){	
		
		LoginResponse response = new LoginResponse();
		HttpSession currentSession = servletRequest.getSession();
		loginService.checkUser(lr,response,currentSession);
		return response;
		
	}
	
	@RequestMapping(value = "/getCredentials", method = RequestMethod.POST)
	@Transactional
	public LoginResponse getCredentials(@RequestBody String email){
		
		LoginResponse response = new LoginResponse();
		Boolean state = loginService.getCredentials(email);
		
		if(state){
			response.setCode(200);
			response.setCodeMessage("Credentials send, please check your email.");
		}else{
			response.setCode(401);
			response.setCodeMessage("Invalid email.");
		}

		return response;
		
	}
	
	
	
	
}