package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ironthrone.lyra.contracts.GradoRequest;
import com.ironthrone.lyra.ejb.Grado;
import com.ironthrone.lyra.pojo.GradoPOJO;
import com.ironthrone.lyra.repositories.GradoRepository;

@Service
public class GradoService implements GradoServiceInterface {
	
	@Autowired GradoRepository gradeRepository;
	
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param grados representa una lista de grados tipo ejb
	 * @return UserInterfaceGrados representa una lista de grados tipo POJO.
	 */
	private List<GradoPOJO> generateGradeDtos(List<Grado> grados) {
		
		List<GradoPOJO> uiGrados = new ArrayList<GradoPOJO>();

		grados.stream().forEach(g -> {
			GradoPOJO dto = new GradoPOJO();
			BeanUtils.copyProperties(g,dto);
			dto.setActiveGr(g.getIsActiveGr());
			dto.setMaterias(null);
			dto.setSeccions(null);
			uiGrados.add(dto);
		});	
		
		return uiGrados;
	}
	
	
	/**
	 * Retorna una lista de grados.
	 * @return lista de grados POJO
	 */
	@Override
	@Transactional
	public List<GradoPOJO> getAll() {
		List<Grado> grados =  gradeRepository.findAll();
		return generateGradeDtos(grados);
	}


	/**
	 * Retorna un grado especifico, dado su ID
	 * @param idGrado identificador del grado
	 * @return grado de tipo POJO
	 */
	@Override
	@Transactional
	public GradoPOJO getGradoById(int idGrado) {
		Grado grado = gradeRepository.findOne(idGrado);
		GradoPOJO dto = new GradoPOJO();
		
		BeanUtils.copyProperties(grado,dto);
		dto.setActiveGr(grado.getIsActiveGr());
		dto.setMaterias(null);
		dto.setSeccions(null);
		
		return dto;
	}

	/**
	 * Retorna un grado especifico, dado su ID
	 * @param idGrado identificador del grado
	 * @return grado de tipo ejb
	 */
	@Override
	@Transactional
	public Grado findById(int idGrado) {
		return gradeRepository.findOne(idGrado);
	}

	/**
	 * Guarda los datos de un grado.
	 * @param grado request de la capa frontend.
	 * @return booleano, true = success, false = error.
	 */
	@Override
	@Transactional
	public Boolean saveGrado(GradoRequest gr) {
		
		Grado newGrado = new Grado();
		Grado ngrade = null;
		
		/**
		 * Seguir los mismos
		 * pasos que en usuarioService
		 * mas adelante para agregarle secciones
		 * y las materias.
		 */
		
		BeanUtils.copyProperties(gr.getGrado(), newGrado);
		
		if(gr.getGrado().getIdGrado() <= -1){
			newGrado.setIdGrado(0);
			newGrado.setIsActiveGr(true);
			ngrade = gradeRepository.save(newGrado);
			
		}else{		
			Grado oldGrado = findById(gr.getGrado().getIdGrado());
			oldGrado = assignProperties(oldGrado, gr.getGrado());
			ngrade = gradeRepository.save(oldGrado);
			
		}
		
		return (ngrade == null) ? false : true;
	}

	
	/**
	 * Copia los nuevos datos traidos de la IU a el objeto que se va a salvar en la base
	 * @param DbGrado grado que se salvara a la base
	 * @param UiGrado gradoPOJO de la Interfaz
	 * @return el grado de base con los atributos actualizados.
	 */
	
	private Grado assignProperties(Grado DbGrado, GradoPOJO UiGrado){
		
		DbGrado.setNombre(UiGrado.getNombre());
		DbGrado.setDescripcion(UiGrado.getDescripcion());
		DbGrado.setIsActiveGr(UiGrado.getIsActiveGr());
		
		return DbGrado;
	}
	
	
}
