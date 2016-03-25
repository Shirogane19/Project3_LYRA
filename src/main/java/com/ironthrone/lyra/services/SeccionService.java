package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.SeccionRequest;
import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.ejb.Grado;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Seccion;
import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.GradoPOJO;
import com.ironthrone.lyra.pojo.SeccionPOJO;
import com.ironthrone.lyra.repositories.AlumnoRepository;
import com.ironthrone.lyra.repositories.GradoRepository;
import com.ironthrone.lyra.repositories.SeccionRepository;

@Service
public class SeccionService implements SeccionServiceInterface{
	@Autowired private SeccionRepository seccionRepository;
	@Autowired private GradoRepository gradeRepository;
	@Autowired private AlumnoRepository alumnoRepository;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param secciones tipo ejbs
	 * @return lista de secciones POJO.
	 */
	private List<SeccionPOJO> generateSeccionesDtos(List<Seccion> secciones){
		
		List<SeccionPOJO> uiSecciones = new ArrayList<SeccionPOJO>();
		
		secciones.stream().forEach(s -> {
			SeccionPOJO dto = new SeccionPOJO();
			BeanUtils.copyProperties(s,dto);
			dto.setActiveSec(s.getIsActiveSec());
			uiSecciones.add(dto);
		});	
		
		return uiSecciones;
	};
	
	/**
	 * Retorna una lista de objetos SeccionPOJO
	 * @param SeccionRequest
	 * @return List<SeccionPOJO> 
	 */
	@Override
	public List<SeccionPOJO> getAll(SeccionRequest ur) {
		List<Seccion> secciones = seccionRepository.findAll();
		
		return generateSeccionesDtos(secciones);
	}

	/**
	 * Retorna una lista de objetos SeccionPOJO en estado activo
	 * @return List<SeccionPOJO> 
	 */
	@Override
	public List<SeccionPOJO> getActiveSecciones() {
		List<Seccion> secciones =  seccionRepository.findByisActiveSecTrue();
		
		return generateSeccionesDtos(secciones);

	}
	
	/**
	 * Retorna una lista de objetos SeccionPOJO en estado inactivo
	 * @return List<SeccionPOJO> 
	 */
	@Override
	public List<SeccionPOJO> getInactiveSecciones() {
		List<Seccion> secciones =  seccionRepository.findByisActiveSecFalse();
		
		return generateSeccionesDtos(secciones);
	}
	
	/**
	 * Obtiene un objeto SeccionPojo por el id de una seccion
	 * @param INT
	 * @return SeccionPOJO
	 */
	@Override
	public SeccionPOJO getSeccionById(int idSeccion) {
		Seccion seccion =  seccionRepository.findOne(idSeccion);
		SeccionPOJO dto = new SeccionPOJO();
		
		BeanUtils.copyProperties(seccion,dto);
		dto.setActiveSec(seccion.getIsActiveSec());
		dto.setAlumnos(generateAlumnoDto(seccion));
		dto.setGrado(generateGradoDto(seccion));
		dto.setProfesorSeccions(null);
	
		return dto;
	}
	
	/**
	 * Obtiene un objeto SeccionEJB por el id de una seccion
	 * @param INT
	 * @return Seccion EBJ
	 */
	@Override
	public Seccion findById(int idSeccion) {
		return seccionRepository.findOne(idSeccion);
	}
	
	/**
	 * Retorna un boolean true se salvo, false no se salvo
	 * @param SeccionRequest
	 * @return Boolean
	 */
	@Override
	public Boolean saveSeccion(SeccionRequest ur) {
		Seccion newSeccion = new Seccion();
		Seccion nseccion = null;

		BeanUtils.copyProperties(ur.getSeccion(), newSeccion);	
		newSeccion.setIsActiveSec(ur.getSeccion().isActiveSec());
		
		int idGrado = ur.getSeccion().getGrado().getIdGrado();
		Grado grado = gradeRepository.findOne(idGrado);
		newSeccion.setGrado(grado);
		
		if(ur.getSeccion().getIdSeccion() <= -1){		
			
			nseccion = seccionRepository.save(newSeccion);
		 
		}else{	
			
			Seccion oldSec = findById(ur.getSeccion().getIdSeccion());
			
			if(ur.getSeccion().getAlumnos() == null){
				BeanUtils.copyProperties(newSeccion, oldSec);
			}
			
			oldSec.setIsActiveSec(newSeccion.getIsActiveSec());
			
			if(ur.getSeccion().getAlumnos() != null){
				desasignarAlumnosSecciones(ur);
				ur.getSeccion().getAlumnos().stream().forEach( a -> {
					Alumno oldAlumno = alumnoRepository.findOne(a.getIdAlumno());
					oldAlumno.setSeccion(oldSec);
				});
			}
			
			nseccion = seccionRepository.save(oldSec);	
		}
		return (nseccion == null) ? false : true;
	}

	/**
	 * Obtiene una lista de AlumnoPojo de una seccion
	 * @param Institucion
	 * @return List<AlumnoPOJO>
	 */
	private List<AlumnoPOJO> generateAlumnoDto(Seccion s) {
		
		List<AlumnoPOJO> alumnos = new ArrayList<AlumnoPOJO>();
		
		s.getAlumnos().stream().forEach(a -> {
			AlumnoPOJO alumno = new AlumnoPOJO();
			BeanUtils.copyProperties(a, alumno);	
			alumno.setRegistrosMedicos(null);
			alumno.setSeccion(null);
			alumno.setUsuarios(null);
			alumno.setActiveAl(a.getIsActiveAl());
			alumnos.add(alumno);
		});	

		return alumnos;
	};
	
	/**
	 * Remueve la seccion anterior en los alumnos
	 * @param ur
	 */
	private void desasignarAlumnosSecciones(SeccionRequest ur) {
		
		Seccion s = findById(ur.getSeccion().getIdSeccion());
		List<Alumno> aList = new ArrayList<Alumno>();
		aList = s.getAlumnos();
		
		System.out.println(s);
		System.out.println(aList);
		System.out.println(aList.size());
		
		s.getAlumnos().stream().forEach( a -> {
			a.setSeccion(null);
		
		});
			
	}
	
	/**
	 * Retorna una objeto de tipo GradoPOJO
	 * @param Seccion
	 * @return GradoPOJO
	 */
	private GradoPOJO generateGradoDto(Seccion s) {
		
		Grado grado = gradeRepository.findOne(s.getGrado().getIdGrado());
		GradoPOJO dto = new GradoPOJO();
		BeanUtils.copyProperties(grado,dto);
		dto.setActiveGr(grado.getIsActiveGr());
		dto.setMaterias(null);
		dto.setSeccions(null);
		
		return dto;
	}
	



}
