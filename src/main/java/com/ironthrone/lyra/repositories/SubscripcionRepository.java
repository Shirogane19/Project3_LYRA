package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Subscripcion;

public interface SubscripcionRepository extends CrudRepository<Subscripcion,Integer>{
	
	List<Subscripcion>findAll();

}
