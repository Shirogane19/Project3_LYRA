package com.ironthrone.lyra.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ironthrone.lyra.ejb.Tarea;
import com.ironthrone.lyra.ejb.Usuario;

public interface TareaRepository extends CrudRepository<Tarea,Integer>{
	
	List<Tarea>findAll();
	List<Tarea>findByisActiveTaTrue();
	List<Tarea>findByisActiveTaFalse();
	List<Tarea>findByUsuariosIn(Usuario u);
    /*@Query("SELECT t.idTarea FROM Tarea t where t.Usuario_idUsuario = :_idUser")
    int[] findTareaByUser(@Param("_idUser") int idUser); */

}
