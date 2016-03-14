package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.InstitucionRequest;
import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.ejb.Bitacora;
import com.ironthrone.lyra.ejb.Grado;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Materia;
import com.ironthrone.lyra.ejb.Subscripcion;
import com.ironthrone.lyra.ejb.Usuario;
import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.InstitucionPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;
import com.ironthrone.lyra.repositories.InstitucionRepository;
import com.ironthrone.lyra.repositories.UsuarioRepository;

@Service
public class InstitucionService implements InstitucionServiceInterface{

	@Autowired private InstitucionRepository institucionRepository;
	
	private List<InstitucionPOJO> generateInstitucionDtos(List<Institucion> instituciones){
		
		List<InstitucionPOJO> institucionesPojo = new ArrayList<InstitucionPOJO>();
		
		instituciones.stream().forEach(i -> {
			InstitucionPOJO dto = new InstitucionPOJO();
			BeanUtils.copyProperties(i,dto);
			dto.setHasSuscripcion(i.getHasSuscripcion());
			institucionesPojo.add(dto);
		});	
		
		return institucionesPojo;
	};

	/**
	 * Retorna una lista de instituciones.
	 * @return lista de instituciones POJO
	 */
	@Override
	@Transactional
	public List<InstitucionPOJO> getAll() {
		List<Institucion> instituciones = institucionRepository.findAll();
		return generateInstitucionDtos(instituciones);
	}

	/**
	 * Guarda los datos de una Institución.
	 * @param InstitucionRequest de la capa frontend.
	 * @return booleano, true = success, false = error.
	 */
	@Override
	@Transactional
	public Boolean saveInstitucion(InstitucionRequest institucionRequest) {
		
		Institucion institucion = new Institucion();
		Institucion ninstitucionT = null;
		BeanUtils.copyProperties(institucionRequest.getInstitucion(), institucion);
		
		institucion.setAlumnos(new ArrayList<Alumno>());
		institucion.setBitacoras(new ArrayList<Bitacora>());
		institucion.setGrados(new ArrayList<Grado>());
		institucion.setMaterias(new ArrayList<Materia>());
		institucion.setSubscripcions(new ArrayList<Subscripcion>());
		institucion.setUsuarios(new ArrayList<Usuario>());
		
		if(institucionRequest.getInstitucion().getIdInstitucion() <= -1){		
			
			institucion.setHasSuscripcion(true);
			ninstitucionT = institucionRepository.save(institucion);
			
		}else{		
			Institucion oldInstitucion = findById(institucion.getIdInstitucion());
			
			oldInstitucion.setHasSuscripcion(institucion.getHasSuscripcion());
			oldInstitucion.setLogoInstitucion(institucion.getLogoInstitucion());
			oldInstitucion.setNombreInstitucion(institucion.getNombreInstitucion());
			oldInstitucion.setAlumnos(institucion.getAlumnos());
			oldInstitucion.setBitacoras(institucion.getBitacoras());
			oldInstitucion.setGrados(institucion.getGrados());
			oldInstitucion.setMaterias(institucion.getMaterias());
			oldInstitucion.setSubscripcions(institucion.getSubscripcions());
			oldInstitucion.setUsuarios(institucion.getUsuarios());
			
			ninstitucionT = institucionRepository.save(oldInstitucion);	
		}

		return (ninstitucionT == null) ? false : true;
		
	}

	/**
	 * Retorna los detalles de una institución.
	 * @param idInstitucion, identificador único de la institución.
	 * @return Institución de tipo InstitucionPOJO.
	 */
	@Override
	@Transactional
	public InstitucionPOJO getInstitucionById(int idInstitucion) {
		
		Institucion institucion =  institucionRepository.findOne(idInstitucion);
		InstitucionPOJO dto = new InstitucionPOJO();
		BeanUtils.copyProperties(institucion,dto);
		
		return dto;
	}
	
	/**
	 * Retorna una única institución por ID.
	 * @param idInstitucion, identificador único de la institución.
	 * @return Usuario de tipo UsuarioEJB.
	 */
	@Override
	@Transactional
	public Institucion findById(int idInstitucion) {	
		return institucionRepository.findOne(idInstitucion);
	}
}
