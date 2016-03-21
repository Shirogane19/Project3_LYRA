package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ironthrone.lyra.ejb.Rol;
import com.ironthrone.lyra.pojo.RolPOJO;
import com.ironthrone.lyra.pojo.TareaPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.RolRepository;

/**
 * Clase que proporciona los servicios de rol al controlador Rol.
 * @author jeanpaul
 *
 */

@Service
public class RolService implements RolServiceInterface {
	

	@Autowired private RolRepository rolRepository;

	@Override
	@Transactional
	public List<RolPOJO> getAll() {

		List<Rol> roles = rolRepository.findAll();
		return generateRolDtos(roles);
	}

	@Override
	@Transactional
	public List<RolPOJO> findByUsuario(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public List<RolPOJO> findByTarea(int idTarea) {
		// TODO Auto-generated method stub
		return null;
	}
	

	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param users representa una lista de roles tipo ejb
	 * @return UserInterfaceUsers, lista de roles POJO.
	 */
	private List<RolPOJO> generateRolDtos(List<Rol> roles){
		
		List<RolPOJO> uiRoles = new ArrayList<RolPOJO>();

		
		roles.stream().forEach(r -> {
			RolPOJO dto = new RolPOJO();
			BeanUtils.copyProperties(r,dto);
			
			dto.setTareas(generateTaskDto(r));
			dto.setUsuarios(generateUserDto(r));
			dto.setActiveRol(r.getIsActiveRol());
			uiRoles.add(dto);
		});	
		
		return uiRoles;
	}

	private List<TareaPOJO> generateTaskDto(Rol r) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<UsuarioPOJO> generateUserDto(Rol r) {
		
		List<UsuarioPOJO> users = new ArrayList<UsuarioPOJO>();
		
		r.getUsuarios().stream().forEach(u -> {
			UsuarioPOJO user = new UsuarioPOJO();
			BeanUtils.copyProperties(u, user);	
			user.setPassword("secret");
			user.setActiveUs(u.getIsActiveUs());
			user.setRols(null);
			user.setInstitucion(null);
			
			users.add(user);
		});	

		return users;
	};

}
