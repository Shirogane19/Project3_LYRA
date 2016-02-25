package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.UsuarioRequest;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.UsuarioPOJO;


public interface UsuarioServiceInterface {

	List<UsuarioPOJO> getAll(UsuarioRequest ur);
	List<UsuarioPOJO> getActiveUsers();
	List<UsuarioPOJO> getInactiveUsers();
	UsuarioPOJO getUserById(int idUsuario);
	Usuario findById(int idUsuario);
	Boolean saveUser(UsuarioRequest ur);


}
