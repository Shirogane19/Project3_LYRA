package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.AlumnoRequest;
import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Seccion;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.AlumnoRepository;

@Service
public class AlumnoService implements AlumnoServiceInterface{

	@Autowired private AlumnoRepository alumnoRepository;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param users representa una lista de alumnos tipo ejb
	 * @return Lista de alumno POJO.
	 */
	private List<AlumnoPOJO> generateAlumnosDtos(List<Alumno> alumnos){
		
		List<AlumnoPOJO> uiAlumnos = new ArrayList<AlumnoPOJO>();
		
		alumnos.stream().forEach(a -> {
			AlumnoPOJO dto = new AlumnoPOJO();
			BeanUtils.copyProperties(a,dto);
			dto.setActiveAl(a.getIsActiveAl());
			uiAlumnos.add(dto);
		});	
		
		return uiAlumnos;
	};
	
	/**
	 * Retorna una lista de Alumnos.
	 * @return lista de alumnos POJO
	 */
	@Override
	@Transactional
	public List<AlumnoPOJO> getAll() {
		List<Alumno> alumnos = alumnoRepository.findAll();
		return generateAlumnosDtos(alumnos);
	}
	
	/**
	 * Retorna un objeto AlumnoPOJO.
	 * @param idAlumno, identificador único de alumno.
	 * @return Alumno de tipo AlumnoPOJO.
	 */
	@Override
	@Transactional
	public AlumnoPOJO getAlumnoById(int idAlumno) {
		
		Alumno alumno =  alumnoRepository.findOne(idAlumno);
		AlumnoPOJO dto = new AlumnoPOJO();
		
		BeanUtils.copyProperties(alumno,dto);
	
		return dto;
	}
	
	/**
	 * Guarda los datos de un Alumno.
	 * @param AlumnoRequest de la capa frontend.
	 * @return booleano, true = success, false = error.
	 */
	@Override
	@Transactional
	public Boolean saveAlumno(AlumnoRequest alumnoRequest){
		
		Alumno newAlumno = new Alumno();
		Alumno nalumnoT = null;
		BeanUtils.copyProperties(alumnoRequest.getAlumno(), newAlumno);
	
		//**********************************************CAMBIAR POR get ID
		//Institucion ins = new Institucion();	
		//ins.setIdInstitucion(1);
		//alumno.setInstitucion(ins);
		//BeanUtils.copyProperties(alumnoRequest.getAlumno().getInstitucion(), alumno.getInstitucion());

		//Seccion seccion = new Seccion();
		//seccion.setIdSeccion(1);
		//alumno.setSeccion(seccion);
		//BeanUtils.copyProperties(alumnoRequest.getAlumno().getSeccion(), alumno.getSeccion());
		//***********************************************
		
		if(alumnoRequest.getAlumno().getIdAlumno() <= -1){		
	
			nalumnoT = alumnoRepository.save(newAlumno);
			
		}else{		
			Alumno oldAlumno = findById(newAlumno.getIdAlumno());
			
			BeanUtils.copyProperties(newAlumno, oldAlumno);
			oldAlumno.setIsActiveAl(alumnoRequest.getAlumno().isActiveAl());
			
			nalumnoT = alumnoRepository.save(oldAlumno);	
		}

		return (nalumnoT == null) ? false : true;
		
	}

	/**
	 * Retorna un único alumno por ID.
	 * @param idAlumno, identificador único de alumno.
	 * @return Alumno de tipo AlumnoEJB.
	 */
	@Override
	public Alumno findById(int idAlumno) {
		return alumnoRepository.findOne(idAlumno);
	}

}
