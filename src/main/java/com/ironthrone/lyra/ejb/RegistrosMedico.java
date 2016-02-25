package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the registros_medicos database table.
 * 
 */
@Entity
@Table(name="registros_medicos")
@NamedQuery(name="RegistrosMedico.findAll", query="SELECT r FROM RegistrosMedico r")
public class RegistrosMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRegistros_Medicos;

	private String descripcion;

	@Column(name="nombre_registro")
	private String nombreRegistro;

	//bi-directional many-to-one association to HistorialMedico
	@ManyToOne
	@JoinColumn(name="Historial_Medico_idHistorial_Medico")
	private HistorialMedico historialMedico;

	public RegistrosMedico() {
	}

	public int getIdRegistros_Medicos() {
		return this.idRegistros_Medicos;
	}

	public void setIdRegistros_Medicos(int idRegistros_Medicos) {
		this.idRegistros_Medicos = idRegistros_Medicos;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombreRegistro() {
		return this.nombreRegistro;
	}

	public void setNombreRegistro(String nombreRegistro) {
		this.nombreRegistro = nombreRegistro;
	}

	public HistorialMedico getHistorialMedico() {
		return this.historialMedico;
	}

	public void setHistorialMedico(HistorialMedico historialMedico) {
		this.historialMedico = historialMedico;
	}

}