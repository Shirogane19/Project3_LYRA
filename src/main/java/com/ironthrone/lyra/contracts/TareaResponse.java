package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.TareaPOJO;

public class TareaResponse extends BaseResponse{
	
	private List<TareaPOJO> tareas;
	private TareaPOJO tarea;
	
	
	public TareaResponse() {
		super();
	}
	
	public List<TareaPOJO> getTareas() {
		return tareas;
	}

	public void setTareas(List<TareaPOJO> tareas) {
		this.tareas = tareas;
	}

	public TareaPOJO getTarea() {
		return tarea;
	}

	public void setTarea(TareaPOJO tarea) {
		this.tarea = tarea;
	}
	
	

}
