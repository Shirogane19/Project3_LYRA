package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironthrone.lyra.contracts.MateriaRequest;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Materia;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.MateriaPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.InstitucionRepository;
import com.ironthrone.lyra.repositories.MateriaRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;


@Service
public class MateriaService implements MateriaServiceInterface{
	
	@Autowired private MateriaRepository materiaRepository;
	@Autowired private InstitucionRepository institucionRepository;
	@Autowired private UsuarioRepository userRepository;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param materias tipo ejbs
	 * @return lista de materias POJO.
	 */
	private List<MateriaPOJO> generateMateriasDtos(List<Materia> materias){
		
		List<MateriaPOJO> uiMaterias = new ArrayList<MateriaPOJO>();
		
		materias.stream().forEach(m -> {
			MateriaPOJO dto = new MateriaPOJO();
			BeanUtils.copyProperties(m,dto);
			dto.setActiveMat(m.getIsActiveMat());
			uiMaterias.add(dto);
		});	
		
		return uiMaterias;
	};
	
	/**
	 * Retorna una lista de materias.
	 * @param materia request.
	 * @return lista de materias POJO
	 */
	
	@Override
	@Transactional
	public List<MateriaPOJO> getAll(MateriaRequest ur) {
		List<Materia> materias = materiaRepository.findAll();
		
		return generateMateriasDtos(materias);
	}

	@Override
	public List<MateriaPOJO> getActiveMaterias() {
		// TODO Auto-generated method stub
		List<Materia> materias =  materiaRepository.findByisActiveMatTrue();
		
		return generateMateriasDtos(materias);

	}

	@Override
	public List<MateriaPOJO> getInactiveMaterias() {
		List<Materia> materias =  materiaRepository.findByisActiveMatFalse();
		
		return generateMateriasDtos(materias);
	}

	@Override
	public MateriaPOJO getMateriaById(int idMateria) {
		Materia materia =  materiaRepository.findOne(idMateria);
		MateriaPOJO dto = new MateriaPOJO();
		
		BeanUtils.copyProperties(materia,dto);
		dto.setActiveMat(materia.getIsActiveMat());
	
		return dto;
	}

	@Override
	public Materia findById(int idMateria) {
		
		return materiaRepository.findOne(idMateria);
	}

	@Override
	public Boolean saveMateria(MateriaRequest ur) {
		Materia newMateria = new Materia();
		Materia nmateria = null;
		List<Usuario> userList = new ArrayList<Usuario>();

		System.out.println("Request Materia: " + ur.getMateria().isActiveMat());
		
		BeanUtils.copyProperties(ur.getMateria(), newMateria);	
		newMateria.setIsActiveMat(ur.getMateria().isActiveMat());
		
		int idInstitucion = ur.getMateria().getInstitucion().getIdInstitucion();
		Institucion i = institucionRepository.findOne(idInstitucion);
		newMateria.setInstitucion(i);
		
		
		if(ur.getMateria().getIdMateria() <= -1){		
			nmateria = materiaRepository.save(newMateria);
		 
		}else{		
			Materia oldMat = findById(newMateria.getIdMateria());
			
			
			if(ur.getMateria().getProfesorMateria() == null){
				oldMat.setIdMateria(newMateria.getIdMateria());
				oldMat.setIsActiveMat(newMateria.getIsActiveMat());
			}
			
			oldMat.setIsActiveMat(newMateria.getIsActiveMat());
			
			if(ur.getMateria().getProfesorMateria() != null){

				ur.getMateria().getProfesorMateria().stream().forEach( p -> {
					Usuario u = userRepository.findOne(p.getIdUsuario());
					userList.add(u);
				});
				
				oldMat.setUsuarios(userList);
			}
			
			nmateria = materiaRepository.save(oldMat);	
		}
		return (nmateria == null) ? false : true;
	}

	/**
	 * Retorna un objeto MateriaPOJO con sus profesores
	 * @param Integer
	 * @return SeccionPOJO
	 */
	@Override
	@Transactional
	public MateriaPOJO getProfes(int idMateria) {
		
		Materia m = materiaRepository.findOne(idMateria);
		MateriaPOJO dto = new MateriaPOJO();
		
		dto.setIdMateria(m.getIdMateria());
		dto.setNombre(m.getNombre());
		dto.setActiveMat(m.getIsActiveMat());
		dto.setProfesorMateria(generateUserDto(m.getUsuarios()));
	
		return dto;
	}

	/**
	 * Retorna una lista de Usuarios POJO de una institución
	 * @param Institucion recibe un objeto Institución
	 * @return List<UsuarioPOJO> Lista de usuario de tipo POJO
	 */
	private List<UsuarioPOJO> generateUserDto(List<Usuario> usp) {
		
		List<UsuarioPOJO> users = new ArrayList<UsuarioPOJO>();
		
		usp.stream().forEach(u -> {
			
			UsuarioPOJO user = new UsuarioPOJO();
			BeanUtils.copyProperties(u, user);	
			user.setPassword("secret");
			user.setActiveUs(u.getIsActiveUs());
			user.setRols(null);
			user.setIdTareas(null);
			user.setInstitucion(null);
			user.setMaterias(null);
			user.setSeccions(null);
			user.setAlumnos(null);
			user.setPeriodo(null);
			user.setListaInstituciones(null);
			user.setTareas(null);
			
			users.add(user);
		});	

		return users;
	};

}
