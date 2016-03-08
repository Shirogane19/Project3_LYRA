package com.ironthrone.lyra.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.security.IronPasswordEncryption;
import com.ironthrone.lyra.contracts.UsuarioRequest;
import com.ironthrone.lyra.ejb.Rol;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.RolPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.RolRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Clase que proporciona los servicios de usuario al controlador Usuario.
 * @author jeanpaul
 *
 */

@Service
public class UsuarioService implements UsuarioServiceInterface {

	@Autowired private UsuarioRepository usersRepository;
	@Autowired private RolRepository rolRepository;
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
			dto.setRols(generateRolDto(u));
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
		BeanUtils.copyProperties(user, dto);
		dto.setActiveUs(user.getIsActiveUs());
		dto.setRols(generateRolDto(user));

		return dto;
	}
	
	/**
	 * Genera una lista de roles POJO.
	 * @param user al que se le genera la lista.
	 * @return lista de roles pojo
	 */
	
	public List<RolPOJO> generateRolDto(Usuario user){
		
		List<RolPOJO> roles = new ArrayList<RolPOJO>();	
		
		user.getRols().stream().forEach(r -> {
			RolPOJO rol = new RolPOJO();
			BeanUtils.copyProperties(r, rol);			
			rol.setTareas(null);
			rol.setUsuarios(null);
			rol.setActiveRol(r.getIsActiveRol());
			roles.add(rol);
		});	
		
//		roles.stream().forEach(r -> {
//			System.out.print("ROL: " + r.getNombreRol() + " ");
//		});	
		
		return roles;	
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
		List<Rol> rols = new ArrayList<Rol>();
	
		BeanUtils.copyProperties(ur.getUsuario(), newUser);	
		
//		/** Cambiar por getInstitucionByID luego **/
//		Institucion ins = new Institucion();	
//		ins.setIdInstitucion(1);
//		ins.setNombreInstitucion("Cenfotec");
//		newUser.setInstitucion(ins);		
//		/** fin comment **/
		
//		System.out.println(ur.getUsuario().getIdRoles());
		
		ur.getUsuario().getIdRoles().stream().forEach(r -> {	
			int id = Integer.parseInt(r);
			rols.add(getRol(id, newUser));
			newUser.setRols(rols);
				
		});
		
			
		newUser.setPassword("12345");
		newUser.setPassword(encryptor.ironEncryption(newUser.getPassword()));
		
		if(ur.getUsuario().getIdUsuario() <= -1){
			newUser.setIdUsuario(0);
			newUser.setIsActiveUs(true);
			newUser.setDateOfJoin(getCurrentDate());
			nuser = usersRepository.save(newUser);
		 
		}else{		
			Usuario oldUser = findById(newUser.getIdUsuario());
			BeanUtils.copyProperties(newUser, oldUser);
			oldUser.setIsActiveUs(ur.getUsuario().isActiveUs());
			oldUser.setDateOfJoin(newUser.getDateOfJoin());
			BeanUtils.copyProperties(newUser.getRols(), rols);
			oldUser.setRols(rols);
			nuser = usersRepository.save(oldUser);	
		}
		return (nuser == null) ? false : true;
	}
	
	/**
	 * Retorna un rol de usuario por el ID dado.
	 * @param idRol
	 * @return Rol.
	 */
	
	public Rol getRol(int idRol, Usuario user){
		
		List<Usuario> usuarios = new ArrayList<Usuario>();
		Rol rol = rolRepository.findOne(idRol);
		usuarios.add(user);
		rol.setUsuarios(usuarios);
		System.out.println("Before the copy bean: " + rol.getIdRol() + rol.getDescripcionRol());		
		return rol;	
	}
	
	public Date getCurrentDate(){
		
		 //  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   //get current date time with Date()
		   Date date = new Date();
		   return date;
	}
	
	/**
	 * Cuando es nuevo el ID esta vacio, por lo tanto no le puede hacer set...
	 */

}
