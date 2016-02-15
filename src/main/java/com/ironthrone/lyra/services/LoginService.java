package com.ironthrone.lyra.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ironthrone.lyra.contracts.LoginRequest;
import com.ironthrone.lyra.contracts.LoginResponse;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.repositories.LoginRepository;

@Service
public class LoginService implements LoginServiceInterface {

	@Autowired private LoginRepository loginRepository;
	
	@Override
	@Transactional
	public void checkUser(LoginRequest lr, LoginResponse response, HttpSession currentSession) {
		Usuario loggedUser = loginRepository.findByEmailAndPassword(lr.getEmail(), lr.getPassword());
		if(loggedUser == null){
			response.setCode(401);
			response.setErrorMessage("Unauthorized User");
		}else{
			response.setCode(200);
			response.setCodeMessage("User authorized");
			
			//CREATE AND SET THE VALUES FOR THE CONTRACT OBJECT
			response.setUserId(loggedUser.getIdUsuario());
			response.setFirstName(loggedUser.getNombre());
			response.setLastName(loggedUser.getApellido());
			//
			currentSession.setAttribute("userId", loggedUser.getIdUsuario());
		}
	}	

}
