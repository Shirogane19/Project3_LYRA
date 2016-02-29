package com.ironthrone.lyra.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ironthrone.lyra.contracts.MateriaRequest;
import com.ironthrone.lyra.ejb.Materia;
import com.ironthrone.lyra.pojo.MateriaPOJO;
import com.ironthrone.lyra.repositories.MateriaRepository;


@Service
public class MateriaService implements MateriaServiceInterface{
	@Autowired private MateriaRepository materiaRepository;
	
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
			//dto.setActiveMat(m.getIsActiveMat());
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

		BeanUtils.copyProperties(ur.getMateria(), newMateria);	
		newMateria.setIsActiveMat(ur.getMateria().isActiveMat());
		
		
		if(ur.getMateria().getIdMateria() <= -1){		
			nmateria = materiaRepository.save(newMateria);
		 
		}else{		
			Materia oldMat = findById(newMateria.getIdMateria());
			BeanUtils.copyProperties(newMateria, oldMat);
			oldMat.setIsActiveMat(newMateria.getIsActiveMat());
			nmateria = materiaRepository.save(oldMat);	
		}
		return (nmateria == null) ? false : true;
	}

	

}
