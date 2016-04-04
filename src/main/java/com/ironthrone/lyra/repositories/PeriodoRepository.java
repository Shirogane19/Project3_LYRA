package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Periodo;

/**
 * Declara los servicios del repositorio de roles.
 * @author jeanpaul
 *
 */

public interface PeriodoRepository extends CrudRepository<Periodo, Integer> {
	
	List<Periodo>findAll();
	Periodo findByYear(String y);
	Periodo findByIsActivePrTrue();


}