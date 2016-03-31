package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Alumno;
import com.ironthrone.lyra.ejb.RegistrosMedico;

/**
 * Declara los servicios del repositorio de registros medicos.
 * @author jeanpaul
 *
 */

public interface RegistroMedicoRepository extends CrudRepository<RegistrosMedico, Integer> {
	
	List<RegistrosMedico> findByAlumno(Alumno a);

}
