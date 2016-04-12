package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.TareaRequest;
import com.ironthrone.lyra.ejb.Tarea;
import com.ironthrone.lyra.pojo.TareaPOJO;

public interface TareaServiceInterface {
	List<TareaPOJO> getAll(TareaRequest ur);
	List<TareaPOJO> getActiveTareas();
	List<TareaPOJO> getInactiveTareas();
	TareaPOJO getTareaById(int idTarea);
	Tarea findById(int idTarea);
	Boolean saveTarea(TareaRequest ur);
	List<TareaPOJO> getTareaByUsuario(int idUsuario);

}
