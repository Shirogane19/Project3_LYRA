package com.ironthrone.lyra.pojo;

import java.util.List;

import com.ironthrone.lyra.ejb.Alumno;

public class HistorialMedicoPOJO {

	private int idHistorial_Medico;
	private Alumno alumno;
	private List<RegistrosMedicoPOJO> registrosMedicos;
	
	public HistorialMedicoPOJO(){
		super();
	}

	public int getIdHistorial_Medico() {
		return idHistorial_Medico;
	}

	public void setIdHistorial_Medico(int idHistorial_Medico) {
		this.idHistorial_Medico = idHistorial_Medico;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<RegistrosMedicoPOJO> getRegistrosMedicos() {
		return registrosMedicos;
	}

	public void setRegistrosMedicos(List<RegistrosMedicoPOJO> registrosMedicos) {
		this.registrosMedicos = registrosMedicos;
	}
	
	
	
}
