package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.MateriaRequest;
import com.ironthrone.lyra.ejb.Materia;
import com.ironthrone.lyra.pojo.MateriaPOJO;

public interface MateriaServiceInterface {
	
	List<MateriaPOJO> getAll(MateriaRequest ur);
	List<MateriaPOJO> getActiveMaterias();
	List<MateriaPOJO> getInactiveMaterias();
	MateriaPOJO getMateriaById(int idMateria);
	Materia findById(int idMateria);
	Boolean saveMateria(MateriaRequest ur);
	MateriaPOJO getProfes(int idMateria);

}
