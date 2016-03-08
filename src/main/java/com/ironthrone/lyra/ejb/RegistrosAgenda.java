package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the registros_agenda database table.
 * 
 */
@Entity
@Table(name="registros_agenda")
@NamedQuery(name="RegistrosAgenda.findAll", query="SELECT r FROM RegistrosAgenda r")
public class RegistrosAgenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idRegistros_Agenda;

	@Column(name="descripcion_registro")
	private String descripcionRegistro;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;

	@Column(name="nombre_registro")
	private String nombreRegistro;

	//bi-directional many-to-one association to Agenda
	@ManyToOne
	private Agenda agenda;

	public RegistrosAgenda() {
	}

	public int getIdRegistros_Agenda() {
		return this.idRegistros_Agenda;
	}

	public void setIdRegistros_Agenda(int idRegistros_Agenda) {
		this.idRegistros_Agenda = idRegistros_Agenda;
	}

	public String getDescripcionRegistro() {
		return this.descripcionRegistro;
	}

	public void setDescripcionRegistro(String descripcionRegistro) {
		this.descripcionRegistro = descripcionRegistro;
	}

	public Date getFechaRegistro() {
		return this.fechaRegistro;
	}

	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public String getNombreRegistro() {
		return this.nombreRegistro;
	}

	public void setNombreRegistro(String nombreRegistro) {
		this.nombreRegistro = nombreRegistro;
	}

	public Agenda getAgenda() {
		return this.agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

}