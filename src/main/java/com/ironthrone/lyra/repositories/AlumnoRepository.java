package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Alumno;

/**
 * Declara los servicios del repositorio de tipo alumno
 * @author Randall
 *
 */
public interface AlumnoRepository extends CrudRepository<Alumno,Integer> {

	List<Alumno> findAll();

}
