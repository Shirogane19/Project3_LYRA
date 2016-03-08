package com.ironthrone.lyra.repositories;


import org.springframework.data.repository.CrudRepository;
import com.ironthrone.lyra.ejb.Usuario;
import java.util.List;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{

	List<Usuario>findAll();
	List<Usuario>findByisActiveUsTrue();
	List<Usuario>findByisActiveUsFalse();
}
