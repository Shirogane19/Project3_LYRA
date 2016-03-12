package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Seccion;

public interface SeccionRepository extends CrudRepository<Seccion,Integer>{

	List<Seccion>findAll();
	List<Seccion>findByisActiveSecTrue();
	List<Seccion>findByisActiveSecFalse();
}
