package com.ironthrone.lyra.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ironthrone.lyra.contracts.LoginRequest;
import com.ironthrone.lyra.contracts.LoginResponse;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.repositories.LoginRepository;
import com.ironthrone.lyra.security.IronPasswordEncryption;
import com.ironthrone.lyra.security.RavenMail;


/**
 * Proporciona los servicios de login y autenticacion al controlador login.
 * @author jeanpaul
 *
 */
@Service
public class LoginService implements LoginServiceInterface {

	@Autowired private LoginRepository loginRepository;
	@Autowired private IronPasswordEncryption encryptor;
	@Autowired private RavenMail raven;	
			   private boolean isActive;
			   private int randomLength = 5;
			   
			 
			   
	@Override
	@Transactional
	public void checkUser(LoginRequest lr, LoginResponse response, HttpSession currentSession) {
	
		Usuario loggedUser = loginRepository.findByEmail(lr.getEmail());
		isActive = loggedUser.getIsActiveUs();
		
		if (encryptor.clashSteel(lr.getPassword(), loggedUser.getPassword())) {
					
				if(isActive){
					response.setCode(200);
					response.setCodeMessage("User authorized");
					
					//CREATE AND SET THE VALUES FOR THE CONTRACT OBJECT
					response.setUserId(loggedUser.getIdUsuario());
					response.setFirstName(loggedUser.getNombre());
					response.setLastName(loggedUser.getApellido());
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


	/**
	 * Cambia las credenciales de logeo de un usuario olvidadizo por una nueva contraseña aleatoria
	 * y le envia un correo con la misma.
	 * @param userMail el email del usuario a modificar.
	 * @return result, si noy hay problemas retorna true, false si el email es erroneo.
	 */
	@Override
	@Transactional
	public boolean getCredentials(String userMail) {
		

		Usuario user = loginRepository.findByEmail(userMail);
		boolean success = false;
		
		System.out.println("ID DEL USUARIO " + user.getIdUsuario());
		
		
		if(user.getIdUsuario() > 0){
			
			String password = encryptor.randomHilt(randomLength);
			String message  = " gracias por contactarnos. Su nueva contraseña es:" + password;
			
			user.setPassword(encryptor.ironEncryption(password));
			loginRepository.save(user);
			raven.SendRavenMessage(user.getEmail(), user.getNombre(), user.getApellido(), message);
			
			success = true;		
			
		}
			
		return success;
	}
}
