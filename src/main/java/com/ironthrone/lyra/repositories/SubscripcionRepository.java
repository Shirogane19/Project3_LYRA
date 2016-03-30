package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Subscripcion;
import com.ironthrone.lyra.ejb.Usuario;

/**
 * Declara los servicios del repositorio de tipo subscripcion
 * @author Randall
 *
 */
public interface SubscripcionRepository extends CrudRepository<Subscripcion,Integer>{
	
	List<Subscripcion>findAll();
	List<Subscripcion>findByInstitucion(int i);
	List<Subscripcion> findByisActiveSubTrue();

}
