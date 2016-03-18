package com.ironthrone.lyra.repositories;


import org.springframework.data.repository.CrudRepository;
import com.ironthrone.lyra.ejb.Rol;
import java.util.List;


public interface RolRepository extends CrudRepository<Rol, Integer> {
	
	List<Rol>findAll();
	Rol findByNombreRol(String nombre);


}
