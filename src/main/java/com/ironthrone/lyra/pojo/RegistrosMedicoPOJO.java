package com.ironthrone.lyra.pojo;

public class RegistrosMedicoPOJO {
	
	private int idRegistros_Medicos;
	private String descripcion;
	private String nombreRegistro;
	private HistorialMedicoPOJO historialMedico;
	
	public RegistrosMedicoPOJO(){
		super();
	}

	public int getIdRegistros_Medicos() {
		return idRegistros_Medicos;
	}

	public void setIdRegistros_Medicos(int idRegistros_Medicos) {
		this.idRegistros_Medicos = idRegistros_Medicos;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreRegistro() {
		return nombreRegistro;
	}

	public void setNombreRegistro(String nombreRegistro) {
		this.nombreRegistro = nombreRegistro;
	}

	public HistorialMedicoPOJO getHistorialMedico() {
		return historialMedico;
	}

	public void setHistorialMedico(HistorialMedicoPOJO historialMedico) {
		this.historialMedico = historialMedico;
	}
	
	

}
