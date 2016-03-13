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
		List<String> idRoles = ur.getUsuario().getIdRoles();
		List<Rol> rols = new ArrayList<Rol>();
		boolean hasRoles = false;

		/**Copia las propiedades del Request a el nuevo Usuario**/
		BeanUtils.copyProperties(ur.getUsuario(), newUser);	
		newUser.setPassword("12345");
		newUser.setPassword(encryptor.ironEncryption(newUser.getPassword()));

		/**Verifica si la lista de roles contiene algo **/
		
		if(!idRoles.isEmpty()){
			hasRoles = true;	
		}
			
		/**Si el Usuario tiene un ID de -1, crea uno nuevo, si no modifica uno existente. **/
		
		if(ur.getUsuario().getIdUsuario() <= -1){
			newUser.setIdUsuario(0);
			newUser.setIsActiveUs(true);
			newUser.setDateOfJoin(getCurrentDate());
			nuser = usersRepository.save(newUser);
			
		/** Si hay roles por agregar, toma el usuario recien creado y le asigna los roles **/
			
			if(hasRoles){
				nuser = assignUserRoles(rols,idRoles,nuser);
			}
	 
		}else{		
			
			Usuario oldUser = findById(newUser.getIdUsuario());
			oldUser = assignProperties(oldUser, ur.getUsuario());
			
			if(hasRoles){
				
				oldUser = removeRoles(oldUser);
				rols = getRoles(idRoles);
				
				System.out.println("Asigno los roles");
				
				oldUser.setRols(rols);
			}
			
		//	oldUser.setIsActiveUs(ur.getUsuario().isActiveUs());
			
			System.out.println("Salvo");
			nuser = usersRepository.save(oldUser);	
		}
		return (nuser == null) ? false : true;
	}
	
	/**
	 *  Copia los nuevos datos traidos de la IU a el objeto que se va a salvar en la base
	 * @param DbUser usuario que se salvara a la base
	 * @param UiUser usuarioPOJO de la Interfaz
	 * @return el usuario de base con los atributos actualizados.
	 */
	
	private Usuario assignProperties(Usuario DbUser, UsuarioPOJO UiUser){
		
		String hash;
		int length = 5;
		
		DbUser.setNombre(UiUser.getNombre());
		DbUser.setApellido(UiUser.getApellido());
		DbUser.setCedula(UiUser.getCedula());
		DbUser.setDateOfJoin(UiUser.getDateOfJoin());
		DbUser.setEmail(UiUser.getEmail());
		DbUser.setPassword(UiUser.getPassword());
		DbUser.setTelefono(UiUser.getTelefono());
		DbUser.setMovil(UiUser.getMovil());
		DbUser.setIsActiveUs(UiUser.isActiveUs());
		
		hash = encryptor.randomHilt(length);
		DbUser.setPassword(encryptor.ironEncryption(hash));
		
		return DbUser;
	}
	
	 /**
	  * 
	  * @param rols es una lista vacia de roles.
	  * @param idRoles es una lista de ID de roles.
	  * @param user el usuario cuyos roles seran asignados.
	  * @return usuario modificado.
	  */
	private Usuario assignUserRoles(List<Rol> rols, List<String> idRoles, Usuario user){
		
		rols = getRoles(idRoles);
		user.setRols(rols);
		user  = usersRepository.save(user);
		
		return user;
	}
	
	
	/**
	 * Metodo que mediante una lista de roles, itera sobre esta y los agrega a la tabla
	 * intermedia de RolesUsuario en la base de datos.
	 * @param listaRoles
	 */
	private List<Rol> getRoles(List<String> idRoles) {
		
		List<Rol> rols = new ArrayList<Rol>();
		
		idRoles.stream().forEach(r -> {	
			
			int id = Integer.parseInt(r);
			Rol rol = rolRepository.findOne(id);
			rols.add(rol);
		
		});
		
		System.out.println("Consigo los nuevos roles" + rols.get(0));
		
		return rols;
		
	}

	/**
	 * Remueve los roles de un usuario antes de ser modificado.
	 * @param listaRoles
	 */
	
	private Usuario removeRoles(Usuario user) {
		
		user.setRols(null);
		user = usersRepository.save(user);
		
		System.out.println("Remuevo roles del usuario viejo" + user.toString());
		
		return user;
 
	}

	/**
	 * Consigue la fecha actual.
	 * @return esta fecha.
	 */
	public Date getCurrentDate(){
		
		 //  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		   //get current date time with Date()
		   Date date = new Date();
		   return date;
	}

	@Override
	public boolean prueba() {

//		List<String> listaRoles = Arrays.asList("1", "2", "3");
//		Usuario user = usersRepository.findOne(1);
//		
//		Rol rol = rolRepository.findOne(4);
//		rol.getUsuarios().remove(1);
//		rolRepository.save(rol);
		

		Usuario user = usersRepository.findOne(4);
		user.setRols(null);
		user = usersRepository.save(user);
		
		List<Rol> roles = new ArrayList<Rol>();
		
		Rol rol1 = rolRepository.findOne(1);
		Rol rol2 = rolRepository.findOne(2);
		roles.add(rol1);
		roles.add(rol2);
		
		user.setRols(roles);
		
		user = usersRepository.save(user);
		
//		Usuario user = new Usuario();
//		user.setPassword("12345");
//		user.setPassword(encryptor.ironEncryption(user.getPassword()));
//		user.setEmail("test@roles.com");
//		user.setNombre("Johnny");
//		user.setApellido("Test");
		
		
		
		return (user == null) ? false : true;
		
		
	//	user.setRols(getRoles(listaRoles, user));

	}
	
	/**
	 * Cuando es nuevo el ID esta vacio, por lo tanto no le puede hacer set...
	 */
	
//	/** Cambiar por getInstitucionByID luego **/
//	Institucion ins = new Institucion();	
//	ins.setIdInstitucion(1);
//	ins.setNombreInstitucion("Cenfotec");
//	newUser.setInstitucion(ins);		
//	/** fin comment **/
	
//	System.out.println(ur.getUsuario().getIdRoles());

}
