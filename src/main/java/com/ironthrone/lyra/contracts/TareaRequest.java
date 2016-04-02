package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.TareaPOJO;

public class TareaRequest {
	
	private TareaPOJO tarea;
	
	public TareaPOJO getTarea() {
		return tarea;
	}

	public void setTarea(TareaPOJO tarea) {
		this.tarea = tarea;
	}
	
	@Override
	public String toString() {
		return "Request de Tarea [user=" + tarea + "]";
	}

}
