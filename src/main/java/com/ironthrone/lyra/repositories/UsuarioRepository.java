package com.ironthrone.lyra.repositories;


import org.springframework.data.repository.CrudRepository;

import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.ejb.Usuario;
import java.util.List;

/**
 * Declara los servicios del repositorio de usuarios.
 * @author jeanpaul
 *
 */
public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	List<Usuario>findAll();
	List<Usuario>findByisActiveUsTrue();
	List<Usuario>findByisActiveUsFalse();
	List<Usuario>findByInstitucionsIn(Institucion ints);
	List<Usuario>findByemail(String email);
	List<Usuario>findBycedula(String cedula);
	
}
