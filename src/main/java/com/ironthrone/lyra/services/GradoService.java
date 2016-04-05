package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ironthrone.lyra.contracts.GradoRequest;
import com.ironthrone.lyra.ejb.Grado;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Materia;
import com.ironthrone.lyra.pojo.GradoPOJO;
import com.ironthrone.lyra.pojo.MateriaPOJO;
import com.ironthrone.lyra.repositories.GradoRepository;
import com.ironthrone.lyra.repositories.InstitucionRepository;
import com.ironthrone.lyra.repositories.MateriaRepository;

@Service
public class GradoService implements GradoServiceInterface {
	
	@Autowired private GradoRepository gradeRepository;
	@Autowired private InstitucionRepository institucionRepository;
	@Autowired private MateriaRepository materiaRepository;
	
	
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
			dto.setMaterias(generateMateriaDtos(g.getMaterias()));
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
		dto.setMaterias(generateMateriaDtos(grado.getMaterias()));
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
		List<String> idMaterias = gr.getGrado().getIdMaterias();
		List<Materia> materias = new ArrayList<Materia>();
		Boolean hasMaterias = false; 
		BeanUtils.copyProperties(gr.getGrado(), newGrado);
		
		int idInstitucion = gr.getGrado().getInstitucion().getIdInstitucion();
		Institucion i = institucionRepository.findOne(idInstitucion);
		newGrado.setInstitucion(i);
		
		
		if(idMaterias != null){
				if(!idMaterias.isEmpty()){
				hasMaterias = true;
			}					
		}

		
		if(gr.getGrado().getIdGrado() <= -1){
			newGrado.setIdGrado(0);
			newGrado.setIsActiveGr(true);
			ngrade = gradeRepository.save(newGrado);
			if(hasMaterias){
				ngrade = assignMateriaGrado(idMaterias, ngrade , materias);
			}
			
		}else{		
			Grado oldGrado = findById(gr.getGrado().getIdGrado());
			oldGrado = removeMaterias(oldGrado);
			if(hasMaterias){
				
				materias = getMaterias(idMaterias);
				oldGrado.setMaterias(materias);	
			}
			
				
			oldGrado = assignProperties(oldGrado, gr.getGrado());
			ngrade = gradeRepository.save(oldGrado);
			
		}
		
		return (ngrade == null) ? false : true;
	}

	
	private Grado assignMateriaGrado(List<String> idMaterias, Grado ngrade, List<Materia> materias) {
		materias = getMaterias(idMaterias);
		ngrade.setMaterias(materias);
		ngrade  = gradeRepository.save(ngrade);
		return ngrade;
	}


	private List<Materia> getMaterias(List<String> idMaterias) {
		List<Materia> materias = new ArrayList<Materia>();
		
		idMaterias.stream().forEach(m -> {	
			
			int id = Integer.parseInt(m);
			Materia mate = materiaRepository.findOne(id);
			materias.add(mate);
		
		});
		return materias;
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
	
	/**
	 * 
	 * @param oldGra
	 * @return
	 */
	private Grado removeMaterias(Grado oldGra) {
		oldGra.setMaterias(null);
		oldGra = gradeRepository.save(oldGra);
		
		return oldGra;
	}
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param materias representa una lista de materia tipo ejb
	 * @return UserInterfaceMaterias representa una lista de materia tipo POJO.
	 */
	private List<MateriaPOJO> generateMateriaDtos(List<Materia> materias) {
		
		List<MateriaPOJO> uiMateria = new ArrayList<MateriaPOJO>();

		materias.stream().forEach(m -> {
			MateriaPOJO dto = new MateriaPOJO();
			dto.setIdMateria(m.getIdMateria());
			dto.setNombre(m.getNombre());
			dto.setActiveMat(m.getIsActiveMat());
			uiMateria.add(dto);
		});	
		
		return uiMateria;
	}
	
	
}
