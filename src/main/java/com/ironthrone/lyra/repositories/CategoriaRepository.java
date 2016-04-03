package com.ironthrone.lyra.repositories;


import org.springframework.data.repository.CrudRepository;
import com.ironthrone.lyra.ejb.Categoria;
import com.ironthrone.lyra.ejb.Institucion;

import java.util.List;

public interface CategoriaRepository extends CrudRepository<Categoria, Integer> {
	
	List<Categoria>findAll();
	List<Categoria>findByInstitucion(Institucion ints);
}
