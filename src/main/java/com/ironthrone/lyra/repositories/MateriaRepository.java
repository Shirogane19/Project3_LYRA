package com.ironthrone.lyra.repositories;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Materia;

public interface MateriaRepository extends CrudRepository<Materia,Integer>{

	List<Materia>findAll();
	List<Materia>findByisActiveMatTrue();
	List<Materia>findByisActiveMatFalse();
}
