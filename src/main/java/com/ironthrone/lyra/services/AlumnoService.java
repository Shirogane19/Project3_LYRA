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
	 * Retorna una lista de Alumnos.
	 * @return lista de alumnos POJO
	 */
	@Override
	@Transactional
	public List<AlumnoPOJO> getAll() {
		List<Alumno> alumnos = alumnoRepository.findAll();
		List<AlumnoPOJO> dtos = new ArrayList<AlumnoPOJO>();
		alumnos.stream().forEach(ta ->{
			AlumnoPOJO dto = new AlumnoPOJO();
			BeanUtils.copyProperties(ta, dto);
			dtos.add(dto);
		});
		return dtos;
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
		
		Alumno alumno = new Alumno();
		Alumno nalumnoT = null;
		BeanUtils.copyProperties(alumnoRequest.getAlumno(), alumno);
	
		//**********************************************CAMBIAR POR get ID
		Institucion ins = new Institucion();	
		ins.setIdInstitucion(1);
		alumno.setInstitucion(ins);
		BeanUtils.copyProperties(alumnoRequest.getAlumno().getInstitucion(), alumno.getInstitucion());

		Seccion seccion = new Seccion();
		seccion.setIdSeccion(1);
		alumno.setSeccion(seccion);
		BeanUtils.copyProperties(alumnoRequest.getAlumno().getSeccion(), alumno.getSeccion());
		//***********************************************
		
		if(alumnoRequest.getAlumno().getIdAlumno() <= -1){		
	
			nalumnoT = alumnoRepository.save(alumno);
			
		}else{		
			Alumno oldAlumno = findById(alumno.getIdAlumno());
			
			oldAlumno.setNombre(alumno.getNombre());
			oldAlumno.setApellido1(alumno.getApellido1());
			oldAlumno.setApellido2(alumno.getApellido2());
			oldAlumno.setCedula(alumno.getCedula());
			oldAlumno.setGenero(alumno.getGenero());
			oldAlumno.setIsActiveAl(alumno.getIsActiveAl());
			oldAlumno.setInstitucion(alumno.getInstitucion());
			oldAlumno.setSeccion(alumno.getSeccion());
			oldAlumno.setEncargadosAlumnos(alumno.getEncargadosAlumnos());
			oldAlumno.setHistorialMedicos(alumno.getHistorialMedicos());
			
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
