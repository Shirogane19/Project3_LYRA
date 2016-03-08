package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Alumno;

public interface AlumnoRepository extends CrudRepository<Alumno,Integer> {

	List<Alumno> findAll();

}
