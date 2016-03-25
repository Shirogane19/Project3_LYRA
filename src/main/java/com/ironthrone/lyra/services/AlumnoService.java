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
import com.ironthrone.lyra.repositories.InstitucionRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;

/**
 * Clase de tipo Alumno, manejo de alumnos y interacción con los repositorios correspondientes
 * @author Randall
 *
 */
@Service
public class AlumnoService implements AlumnoServiceInterface{

	@Autowired private AlumnoRepository alumnoRepository;
	@Autowired private InstitucionRepository institucionRepository;
	@Autowired private UsuarioRepository usersRepository;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param users representa una lista de alumnos tipo ejb
	 * @return UserInterfaceUsers, lista de a POJO.
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
		dto.setUsuarios(generateUserDto(alumno));
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
		
		int idInstitucion = alumnoRequest.getAlumno().getInstitucion().getIdInstitucion();
		Institucion i = institucionRepository.findOne(idInstitucion);
	    newAlumno.setInstitucion(i);
	    
	    //removeEncargados(alumnoRequest);
	    
//	    List<Usuario> usuarios = new ArrayList<Usuario>();
//	    alumnoRequest.getAlumno().getUsuarios().stream().forEach(u ->{
//	    	Usuario usuario = usersRepository.findOne(u.getIdUsuario());
//	    	usuarios.add(usuario);
//	    	
//	    });
//	    newAlumno.setUsuarios(usuarios);
		
		if(alumnoRequest.getAlumno().getIdAlumno() <= -1){		
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
		    alumnoRequest.getAlumno().getUsuarios().stream().forEach(u ->{
		    	Usuario usuario = usersRepository.findOne(u.getIdUsuario());
		    	usuarios.add(usuario);
		    	
		    });
		    newAlumno.setUsuarios(usuarios);
	        
			newAlumno.setIsActiveAl(true);
			nalumnoT = alumnoRepository.save(newAlumno);
			setAlumnoAUsuarios(nalumnoT, alumnoRequest);
			
		}else{	
			
			removeEncargados(alumnoRequest);
			
			List<Usuario> usuarios = new ArrayList<Usuario>();
		    alumnoRequest.getAlumno().getUsuarios().stream().forEach(u ->{
		    	Usuario usuario = usersRepository.findOne(u.getIdUsuario());
		    	usuarios.add(usuario);
		    	
		    });
		    newAlumno.setUsuarios(usuarios);
			
			Alumno oldAlumno = findById(newAlumno.getIdAlumno());
			Seccion oldSeccion = oldAlumno.getSeccion();
			
			BeanUtils.copyProperties(newAlumno, oldAlumno);
			oldAlumno.setIsActiveAl(alumnoRequest.getAlumno().isActiveAl());
			oldAlumno.setSeccion(oldSeccion);
			
			
			nalumnoT = alumnoRepository.save(oldAlumno);	
			setAlumnoAUsuarios(nalumnoT, alumnoRequest);
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
	
	@SuppressWarnings("unused")
	private List<UsuarioPOJO> generateUserDto(Alumno a) {
		
		List<UsuarioPOJO> users = new ArrayList<UsuarioPOJO>();
		
		a.getUsuarios().stream().forEach(u -> {
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
	
	/**
	 * Metodo para setear el alumno a los usuarios
	 * @param Alumno
	 * @param AlumnoRequest
	 */
	private void setAlumnoAUsuarios(Alumno a, AlumnoRequest alumnoRequest){
		
		Alumno alumno = new Alumno();
	    alumnoRequest.getAlumno().getUsuarios().stream().forEach(u ->{
	    	Usuario usuario = usersRepository.findOne(u.getIdUsuario());
	    	List<Alumno> oldAlumnos = usuario.getAlumnos(); 
	    	oldAlumnos.remove(a);
	    	oldAlumnos.add(a);
	    	usuario.setAlumnos(oldAlumnos);
	    	usersRepository.save(usuario);
	    });
		
	}
	
	/**
	 * Metodo para remover un alumno en los usuarios del Alumno
	 * @param AlumnoRequest
	 */
	private void removeEncargados(AlumnoRequest alumnoRequest) {
		
		Alumno a = alumnoRepository.findOne(alumnoRequest.getAlumno().getIdAlumno());
		List<Usuario> us = a.getUsuarios();
		us.stream().forEach(u -> {
			List<Alumno> oldA = u.getAlumnos();
			oldA.remove(a);
			u.setAlumnos(oldA);
			usersRepository.save(u);
		});
	}

}
