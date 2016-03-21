package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.UsuarioRequest;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.UsuarioPOJO;

/**
 * Interfaz de servicio de Usuario
 * @author jeanpaul
 *
 */
public interface UsuarioServiceInterface {

	List<UsuarioPOJO> getAll(UsuarioRequest ur);
	List<UsuarioPOJO> getActiveUsers();
	List<UsuarioPOJO> getInactiveUsers();
	UsuarioPOJO getUserById(int idUsuario);
	Usuario findById(int idUsuario);
	Boolean saveUser(UsuarioRequest ur);
	Boolean editProfile(UsuarioRequest ur);
	List<UsuarioPOJO> prueba();


}
