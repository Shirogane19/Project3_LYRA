package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Institucion;

public interface InstitucionRepository extends CrudRepository<Institucion,Integer>{

	List<Institucion> findAll();
}
