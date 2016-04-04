package com.ironthrone.lyra.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

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
	Usuario findByEmail(String email);
	Usuario findByCedula(String cedula);
	
    @Query("SELECT u.idUsuario FROM Usuario u where u.email = :_email") 
    Integer getUserIdbyEmail(@Param("_email") String mail); 
	
}
