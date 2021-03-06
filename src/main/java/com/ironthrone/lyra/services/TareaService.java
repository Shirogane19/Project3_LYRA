package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.TareaRequest;
import com.ironthrone.lyra.ejb.Categoria;
import com.ironthrone.lyra.ejb.Rol;
import com.ironthrone.lyra.ejb.Tarea;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.RolPOJO;
import com.ironthrone.lyra.pojo.CategoriaPOJO;
import com.ironthrone.lyra.pojo.TareaPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.CategoriaRepository;
import com.ironthrone.lyra.repositories.RolRepository;
import com.ironthrone.lyra.repositories.TareaRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;


@Service
public class TareaService implements TareaServiceInterface{
	
	@Autowired private TareaRepository tareaRepository;
	@Autowired private UsuarioRepository userRepository;
	@Autowired private RolRepository rolRepository;
	@Autowired private CategoriaRepository categoryRepository;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param tareas tipo ejbs
	 * @return lista de tareas POJO.
	 */
	private List<TareaPOJO> generateTareasDtos(List<Tarea> tareas){
		
		List<TareaPOJO> uiTareas = new ArrayList<TareaPOJO>();
		
		tareas.stream().forEach(t -> {
			TareaPOJO dto = new TareaPOJO();

			BeanUtils.copyProperties(t,dto);
			dto.setActiveTa(t.getIsActiveTa());
			dto.setUsuarios(generateUserDto(t));
			dto.setRols(generateRolsDto(t));
			dto.setIdTarea(t.getIdTarea());		
			dto.setTituloTarea(t.getTituloTarea());
			dto.setDescripcionTarea(t.getDescripcionTarea());
			dto.setActiveTa(t.getIsActiveTa());	
			dto.setReadTa(t.getIsReadTa());
			dto.setCategoria(generateCategoryDto(t));

			uiTareas.add(dto);
		});	
		
		return uiTareas;
	};
	

	private List<UsuarioPOJO> generateUserDto(Tarea t) {
		List<UsuarioPOJO> users = new ArrayList<UsuarioPOJO>();	
		
		t.getUsuarios().stream().forEach(u -> {
			UsuarioPOJO dto = new UsuarioPOJO();
			//BeanUtils.copyProperties(u, dto);
			dto.setCedula(u.getCedula());
			dto.setNombre(u.getNombre());
			dto.setApellido(u.getApellido());
			dto.setDateOfJoin(u.getDateOfJoin());
			dto.setIdUsuario(u.getIdUsuario());
			dto.setEmail(u.getEmail());
			dto.setActiveUs(u.getIsActiveUs());
			dto.setMovil(u.getMovil());
			dto.setPassword(u.getPassword());
			dto.setRols(null);
			dto.setInstitucion(null);
			users.add(dto);
		});
		return users;
	}

	private List<RolPOJO> generateRolsDto(Tarea t) {
		List<RolPOJO> rols = new ArrayList<RolPOJO>();	
		
		t.getRols().stream().forEach(r -> {
				RolPOJO dto = new RolPOJO();
			//BeanUtils.copyProperties(u, dto);
				dto.setIdRol(r.getIdRol());
				dto.setNombreRol(r.getNombreRol());
				dto.setDescripcionRol(r.getDescripcionRol());
				dto.setActiveRol(r.getIsActiveRol());
				dto.setTareas(null);
				dto.setUsuarios(null);
			
			rols.add(dto);
		});
		return rols;
	}


	@Override
	@Transactional
	public List<TareaPOJO> getAll(TareaRequest ur) {
		List<Tarea> tareas = tareaRepository.findAll();
		return generateTareasDtos(tareas);
	}

	@Override
	@Transactional
	public List<TareaPOJO> getActiveTareas() {
		List<Tarea> tareas =  tareaRepository.findByisActiveTaTrue();
		
		return generateTareasDtos(tareas);
	}

	@Override
	@Transactional
	public List<TareaPOJO> getInactiveTareas() {
		List<Tarea> tareas =  tareaRepository.findByisActiveTaFalse();
		
		return generateTareasDtos(tareas);
	}

	@Override
	public TareaPOJO getTareaById(int idTarea) {
		Tarea t =  tareaRepository.findOne(idTarea);
		TareaPOJO dto = new TareaPOJO();
		
		dto.setIdTarea(t.getIdTarea());		
		dto.setDescripcionTarea(t.getDescripcionTarea());
		dto.setActiveTa(t.getIsActiveTa());	
		dto.setReadTa(t.getIsReadTa());
		
		dto.setCategoria(generateCategoryDto(t));
		dto.setUsuarios(null);
		dto.setRols(null);


	
		return dto;
	}

	private CategoriaPOJO generateCategoryDto(Tarea tarea) {
		
		Categoria c = tarea.getCategoria();
		CategoriaPOJO dto = new CategoriaPOJO();
		
		dto.setIdCategoria(c.getIdCategoria());
		dto.setNombreCategoria(c.getNombreCategoria());
		dto.setDescripcionCategoria(c.getDescripcionCategoria());
		dto.setActiveCat(c.getIsActiveCat());
		
		return dto;
	}


	@Override
	@Transactional
	public Tarea findById(int idTarea) {
		return tareaRepository.findOne(idTarea);
	}
	
	@Override
	@Transactional
	public Boolean saveTarea(TareaRequest ur) {
		
		List<String> idUsuarios = ur.getTarea().getIdUsuarios();
		List<String> idRoles = ur.getTarea().getIdRols();
		List<Usuario> listUsuario = new ArrayList<Usuario>();
		List<Rol> listRol = new ArrayList<Rol>();
		boolean hasRoles = false;
		boolean hasUsuarios = false;
		
		if(!idUsuarios.isEmpty()){
			hasUsuarios = true;
		}

		if(!idRoles.isEmpty()){
			hasRoles = true;
		}

		
		Tarea newTarea = new Tarea();
		Tarea nTarea = null;

		//BeanUtils.copyProperties(ur.getTarea(), newTarea);	
		//newTarea.setIsActiveTa(ur.getTarea().isActiveTa());
		
		if(ur.getTarea().getIdTarea() <= -1){		
			newTarea = assignProperties(newTarea,ur.getTarea());
			nTarea = tareaRepository.save(newTarea);
			
			if(hasUsuarios){
				nTarea = assignTaskUser(listUsuario,idUsuarios,nTarea);
			}

			if(hasRoles){
				nTarea = assignTaskRole(listRol,idRoles,nTarea);
			}
			
		 
		}else{
			
			System.out.println("ID UI" + ur.getTarea().getIdTarea());
			Tarea oldTa = findById(ur.getTarea().getIdTarea());
			
			System.out.println("ID DBTa" + oldTa.getIdTarea());
			
			oldTa =assignProperties(oldTa, ur.getTarea());
			
			if(hasUsuarios){
				oldTa = removeUsers(oldTa);
				listUsuario = getUsuarios(idUsuarios, listUsuario);
				oldTa.setUsuarios(listUsuario);
			}else{
				oldTa = removeUsers(oldTa);
			}
			
			if(hasRoles){
				oldTa = removeRoles(oldTa);
				listRol = getRole(idRoles, listRol);
				oldTa.setRols(listRol);
			}
			
			
			nTarea = tareaRepository.save(oldTa);	
		}
		return (nTarea == null) ? false : true;
	}
	
	/**
	 * 
	 * @param oldTa
	 * @return
	 */
	private Tarea removeRoles(Tarea oldTa) {
		oldTa.setRols(null);
		oldTa = tareaRepository.save(oldTa);
		
		return oldTa;
	}


	/**
	 * 
	 * @param listRol
	 * @param idRoles
	 * @param nTarea
	 * @return
	 */
	@Transactional
	private Tarea assignTaskRole(List<Rol> listRol, List<String> idRoles, Tarea nTarea) {
		
		listRol = getRole(idRoles,listRol);
		nTarea.setRols(listRol);
		nTarea = tareaRepository.save(nTarea);
		return nTarea;
	}

	/**
	 * 
	 * @param idRoles
	 * @param listRol
	 * @return
	 */
	@Transactional
	private List<Rol> getRole(List<String> idRoles, List<Rol> listRol) {
		
		idRoles.stream().forEach(r -> {
			int id = Integer.parseInt(r);
			Rol rol = rolRepository.findOne(id);
			listRol.add(rol);
			
		});
		return listRol;
	}


	/**
	 * 
	 * @param nTarea
	 * @return
	 */
	@Transactional
	private Tarea removeUsers(Tarea nTarea) {
		
		nTarea.setUsuarios(null);
		nTarea = tareaRepository.save(nTarea);
		
		return nTarea;
	}


	/**
	 * Asigna una lista de usuarios a una tarea
	 * @param listUsuario
	 * @param idUsuarios
	 * @param nTarea
	 * @return tarea con usuarios
	 */
	@Transactional
	private Tarea assignTaskUser(List<Usuario> listUsuario, List<String> idUsuarios, Tarea nTarea) {
		listUsuario = getUsuarios(idUsuarios,listUsuario);
		nTarea.setUsuarios(listUsuario);
		nTarea = tareaRepository.save(nTarea);
		return nTarea;
	}


	/**
	 * Devolver una lista de usuarios de la bd
	 * @param idUsuarios
	 * @param listUsuario
	 * @return
	 */
	@Transactional
	private List<Usuario> getUsuarios(List<String> idUsuarios,List<Usuario> listUsuario) {
		idUsuarios.stream().forEach(u -> {
			int id = Integer.parseInt(u);
			Usuario user = userRepository.findOne(id);
			listUsuario.add(user);
			
		});
		return listUsuario;
	}


	/**
	 * Copia los datos traidos de la ui a un ejb
	 * @param dbTarea ejb
	 * @param uiTarea interfase
	 * @return dbTarea
	 */
	@Transactional
	private Tarea assignProperties(Tarea dbTarea, TareaPOJO uiTarea){
		
		if(uiTarea.getIdCategoria() > 0){
			Categoria category = new Categoria();
			category = categoryRepository.findOne(uiTarea.getIdCategoria());
			dbTarea.setCategoria(category);	
			dbTarea.setDateOfReport(getCurrentDate());
		}

		
		dbTarea.setTituloTarea(uiTarea.getTituloTarea());
		dbTarea.setDescripcionTarea(uiTarea.getDescripcionTarea());
		dbTarea.setIsActiveTa(uiTarea.isActiveTa());
		dbTarea.setIsReadTa(false);

		
		return dbTarea;
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

}
