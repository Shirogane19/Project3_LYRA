package com.ironthrone.lyra.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.security.IronPasswordEncryption;
import com.ironthrone.lyra.contracts.UsuarioRequest;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.UsuarioRepository;

@Service
public class UsuarioService implements UsuarioServiceInterface {

	@Autowired private UsuarioRepository usersRepository;
	@Autowired private IronPasswordEncryption encryptor;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param users representa una lista de usuarios tipo ejb
	 * @return UserInterfaceUsers, lista de usuarios POJO.
	 */
	private List<UsuarioPOJO> generateUserDtos(List<Usuario> users){
		
		List<UsuarioPOJO> uiUsers = new ArrayList<UsuarioPOJO>();
		
		users.stream().forEach(u -> {
			UsuarioPOJO dto = new UsuarioPOJO();
			BeanUtils.copyProperties(u,dto);
			dto.setActiveUs(u.getIsActiveUs());
			uiUsers.add(dto);
		});	
		
		return uiUsers;
	};
	
	
	/**
	 * Retorna una lista de usuarios.
	 * @param user request de la capa frontend.
	 * @return lista de usuarios POJO
	 */
	@Override
	@Transactional
	public List<UsuarioPOJO> getAll(UsuarioRequest ur) {

		List<Usuario> users =  usersRepository.findAll();
		return generateUserDtos(users);
	}
	
	/**
	 * Retorna los detalles de un Usuario..
	 * @param idUsuario, identificador unico de usuario.
	 * @return Usuario de tipo UsuarioPOJO.
	 */
	@Override
	@Transactional
	public UsuarioPOJO getUserById(int idUsuario) {

		Usuario user =  usersRepository.findOne(idUsuario);
		UsuarioPOJO dto = new UsuarioPOJO();
		
		BeanUtils.copyProperties(user,dto);
		dto.setActiveUs(user.getIsActiveUs());
	
		return dto;
	}
	
	/**
	 * Retorna una lista de usuarios activos.
	 * @return Lista de Usuario tipo POJO
	 */
	@Override
	@Transactional
	public List<UsuarioPOJO> getActiveUsers() {

		List<Usuario> users =  usersRepository.findByisActiveUsTrue();
		
		return generateUserDtos(users);
	}
	
	/**
	 * Retorna una lista de usuarios inactivos.
	 * @return Lista de Usuario tipo POJO
	 */
	@Override
	@Transactional
	public List<UsuarioPOJO> getInactiveUsers() {

		List<Usuario> users =  usersRepository.findByisActiveUsFalse();
		
		return generateUserDtos(users);
	}


	/**
	 * Retorna un unico usuario por ID.
	 * @param idUsuario, identificador unico de usuario.
	 * @return Usuario de tipo UsuarioEJB.
	 */
	@Override
	@Transactional
	public Usuario findById(int idUsuario) {
		
		return usersRepository.findOne(idUsuario);
	}

	/**
	 * Guarda los datos de un Usuario.
	 * @param user request de la capa frontend.
	 * @return booleano, true = success, false = error.
	 */
	@Override
	@Transactional
	public Boolean saveUser(UsuarioRequest ur) {

		Usuario newUser = new Usuario();
		Usuario nuser = null;

		BeanUtils.copyProperties(ur.getUsuario(), newUser);	
		newUser.setIsActiveUs(ur.getUsuario().isActiveUs());
		
		/** Cambiar por getInstitucionByID luego **/
		Institucion ins = new Institucion();	
		ins.setIdInstitucion(1);
		ins.setNombreInstitucion("Cenfotec");
		newUser.setInstitucion(ins);		
		/** fin comment **/
		
		newUser.setPassword(encryptor.ironEncryption(newUser.getPassword()));
		
		if(ur.getUsuario().getIdUsuario() <= -1){		
			nuser = usersRepository.save(newUser);
		 
		}else{		
			Usuario oldUser = findById(newUser.getIdUsuario());
			BeanUtils.copyProperties(newUser, oldUser);
			oldUser.setIsActiveUs(newUser.getIsActiveUs());
			nuser = usersRepository.save(oldUser);	
		}
		return (nuser == null) ? false : true;
	}

}
