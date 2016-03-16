package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ironthrone.lyra.contracts.LoginRequest;
import com.ironthrone.lyra.contracts.LoginResponse;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.repositories.LoginRepository;
import com.ironthrone.lyra.security.IronPasswordEncryption;

/**
 * Proporciona los servicios de login y autenticacion al controlador login.
 * @author jeanpaul
 *
 */
@Service
public class LoginService implements LoginServiceInterface {

	@Autowired private LoginRepository loginRepository;
	@Autowired private IronPasswordEncryption encryptor;
			   private boolean isActive;
			   
			   
	@Override
	@Transactional
	public void checkUser(LoginRequest lr, LoginResponse response, HttpSession currentSession) {
	
		Usuario loggedUser = loginRepository.findByEmail(lr.getEmail());
		isActive = loggedUser.getIsActiveUs();
		
		if (encryptor.clashSteel(lr.getPassword(), loggedUser.getPassword())) {
					
				if(isActive){
					response.setCode(200);
					response.setCodeMessage("User authorized");
					List<Integer> idInstitutions = new ArrayList<Integer>();
					
					//CREATE AND SET THE VALUES FOR THE CONTRACT OBJECT
					response.setUserId(loggedUser.getIdUsuario());
					response.setFirstName(loggedUser.getNombre());
					response.setLastName(loggedUser.getApellido());
					loggedUser.getInstitucions().stream().forEach(i -> {
						idInstitutions.add(i.getIdInstitucion());
						System.out.println("********************************");
						System.out.println(i.getIdInstitucion());
						System.out.println("********************************");
					});
					response.setIdInstitucions(idInstitutions);
					//response.setIdInstitucion(loggedUser.getInstitucion().getIdInstitucion());
					
					currentSession.setAttribute("userId", loggedUser.getIdUsuario());
				}else{
					response.setCode(400);
					response.setErrorMessage("User is inactive, please contact your admin.");
					
				}
		
			} else {
				response.setCode(401);
				response.setErrorMessage("Unauthorized User");
			}
		}
}
