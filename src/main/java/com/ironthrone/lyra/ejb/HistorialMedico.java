package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the historial_medico database table.
 * 
 */
@Entity
@Table(name="historial_medico")
@NamedQuery(name="HistorialMedico.findAll", query="SELECT h FROM HistorialMedico h")
public class HistorialMedico implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idHistorial_Medico;

	//bi-directional many-to-one association to Alumno
	@ManyToOne
	private Alumno alumno;

	//bi-directional many-to-one association to RegistrosMedico
	@OneToMany(mappedBy="historialMedico")
	private List<RegistrosMedico> registrosMedicos;

	public HistorialMedico() {
	}

	public int getIdHistorial_Medico() {
		return this.idHistorial_Medico;
	}

	public void setIdHistorial_Medico(int idHistorial_Medico) {
		this.idHistorial_Medico = idHistorial_Medico;
	}

	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public List<RegistrosMedico> getRegistrosMedicos() {
		return this.registrosMedicos;
	}

	public void setRegistrosMedicos(List<RegistrosMedico> registrosMedicos) {
		this.registrosMedicos = registrosMedicos;
	}

	public RegistrosMedico addRegistrosMedico(RegistrosMedico registrosMedico) {
		getRegistrosMedicos().add(registrosMedico);
		registrosMedico.setHistorialMedico(this);

		return registrosMedico;
	}

	public RegistrosMedico removeRegistrosMedico(RegistrosMedico registrosMedico) {
		getRegistrosMedicos().remove(registrosMedico);
		registrosMedico.setHistorialMedico(null);

		return registrosMedico;
	}

}