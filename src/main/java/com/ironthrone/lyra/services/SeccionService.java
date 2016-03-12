package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironthrone.lyra.contracts.SeccionRequest;
import com.ironthrone.lyra.ejb.Seccion;
import com.ironthrone.lyra.pojo.SeccionPOJO;
import com.ironthrone.lyra.repositories.SeccionRepository;

@Service
public class SeccionService implements SeccionServiceInterface{
	@Autowired private SeccionRepository seccionRepository;
	
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
	

	@Override
	public List<SeccionPOJO> getAll(SeccionRequest ur) {
		List<Seccion> secciones = seccionRepository.findAll();
		
		return generateSeccionesDtos(secciones);
	}

	@Override
	public List<SeccionPOJO> getActiveSecciones() {
		List<Seccion> secciones =  seccionRepository.findByisActiveSecTrue();
		
		return generateSeccionesDtos(secciones);

	}

	@Override
	public List<SeccionPOJO> getInactiveSecciones() {
		List<Seccion> secciones =  seccionRepository.findByisActiveSecFalse();
		
		return generateSeccionesDtos(secciones);
	}

	@Override
	public SeccionPOJO getSeccionById(int idSeccion) {
		Seccion seccion =  seccionRepository.findOne(idSeccion);
		SeccionPOJO dto = new SeccionPOJO();
		
		BeanUtils.copyProperties(seccion,dto);
		dto.setActiveSec(seccion.getIsActiveSec());
	
		return dto;
	}

	@Override
	public Seccion findById(int idSeccion) {
		return seccionRepository.findOne(idSeccion);
	}

	@Override
	public Boolean saveSeccion(SeccionRequest ur) {
		Seccion newSeccion = new Seccion();
		Seccion nseccion = null;

		BeanUtils.copyProperties(ur.getSeccion(), newSeccion);	
		newSeccion.setIsActiveSec(ur.getSeccion().isActiveSec());
		
		
		if(ur.getSeccion().getIdSeccion() <= -1){		
			nseccion = seccionRepository.save(newSeccion);
		 
		}else{		
			Seccion oldSec = findById(newSeccion.getIdSeccion());
			BeanUtils.copyProperties(newSeccion, oldSec);
			oldSec.setIsActiveSec(newSeccion.getIsActiveSec());
			nseccion = seccionRepository.save(oldSec);	
		}
		return (nseccion == null) ? false : true;
	}

}
