package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Tarea;

public interface TareaRepository extends CrudRepository<Tarea,Integer>{
	
	List<Tarea>findAll();
	List<Tarea>findByisActiveTaTrue();
	List<Tarea>findByisActiveTaFalse();

}
