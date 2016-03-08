package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ironthrone.lyra.contracts.SubscripcionRequest;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Subscripcion;
import com.ironthrone.lyra.pojo.SubscripcionPOJO;
import com.ironthrone.lyra.repositories.InstitucionRepository;
import com.ironthrone.lyra.repositories.SubscripcionRepository;

@Service
public class SubscripcionService implements SubscripcionServiceInterface{
	
	@Autowired private SubscripcionRepository subscripcionRepository;
	@Autowired private InstitucionRepository institucionRepository;

	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param users representa una lista de subscripciones tipo ejb
	 * @return Lista de Subscripcion POJO.
	 */
	private List<SubscripcionPOJO> generateSubscripcionDtos(List<Subscripcion> subscripcion){
		
		List<SubscripcionPOJO> uiSubscripciones = new ArrayList<SubscripcionPOJO>();
		
		subscripcion.stream().forEach(s -> {
			SubscripcionPOJO dto = new SubscripcionPOJO();
			BeanUtils.copyProperties(s,dto);
			dto.setActiveSub(s.getIsActiveSub());
			uiSubscripciones.add(dto);
		});	
		
		return uiSubscripciones;
	};
	
	/**
	 * Retorna una lista de Subscripcion.
	 * @return lista de Subscripcion POJO
	 */
	@Override
	@Transactional
	public List<SubscripcionPOJO> getAll() {
		List<Subscripcion> subscripciones = subscripcionRepository.findAll();
		return generateSubscripcionDtos(subscripciones);
	}

	/**
	 * Guarda los datos de una Subscripcion.
	 * @param SubscripcionRequest de la capa frontend.
	 * @return booleano, true = success, false = error.
	 */
	@Override
	@Transactional
	public Boolean saveSubscripcion(SubscripcionRequest subscripcionRequest) {
		Subscripcion newSubscripcion = new Subscripcion();
		Subscripcion nsubscripcionT = null;
		BeanUtils.copyProperties(subscripcionRequest.getSubscripcion(), newSubscripcion);
		
		//------------Institucion-------------/
		//Institucion institucion = new Institucion();
		//Institucion ninstitucionT = null;
		//BeanUtils.copyProperties(subscripcionRequest.getSubscripcion().getInstitucion(), institucion);
		
		//ninstitucionT = institucionRepository.save(institucion);
		
		//newSubscripcion.setInstitucion(ninstitucionT);
		//------------------------------------/
		
		if(subscripcionRequest.getSubscripcion().getIdSubscripcion() <= -1){		
	
			nsubscripcionT = subscripcionRepository.save(newSubscripcion);
			
		}else{		
			Subscripcion oldSubscripcion = findById(newSubscripcion.getIdSubscripcion());
			
			BeanUtils.copyProperties(newSubscripcion, oldSubscripcion);
			oldSubscripcion.setIsActiveSub(subscripcionRequest.getSubscripcion().isActiveSub());
			
			nsubscripcionT = subscripcionRepository.save(oldSubscripcion);	
		}

		return (nsubscripcionT == null) ? false : true;
	}

	/**
	 * Retorna un objeto SubscripcionPOJO.
	 * @param idSubscripcionPOJO, identificador único de la Subscripcion.
	 * @return Subscripcion de tipo SubscripcionPOJO.
	 */
	@Override
	@Transactional
	public SubscripcionPOJO getSubscripcionById(int idSubscripcion) {
		Subscripcion subscripcion =  subscripcionRepository.findOne(idSubscripcion);
		SubscripcionPOJO dto = new SubscripcionPOJO();
		
		BeanUtils.copyProperties(subscripcion,dto);
	
		return dto;
	}

	/**
	 * Retorna una única subscripcion por ID.
	 * @param idSubscripcion, identificador único de la subscripción.
	 * @return Subscripción de tipo SubscripcionEJB.
	 */
	@Override
	@Transactional
	public Subscripcion findById(int idSubscripcion) {
		return subscripcionRepository.findOne(idSubscripcion);
	}

}
