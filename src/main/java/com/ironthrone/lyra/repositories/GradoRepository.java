package com.ironthrone.lyra.repositories;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Grado;


/**
 * Interfaz que declara los servicios del repositorio de grados.
 * @author jeanpaul
 *
 */
public interface GradoRepository extends CrudRepository<Grado, Integer>{

	List<Grado>findAll();
	List<Grado>findByYear(String year);
}
