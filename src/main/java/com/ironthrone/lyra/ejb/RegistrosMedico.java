package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import java.util.Date;

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
	
	private int idRegistros_Medicos;
	
	private Date dateOfEvent;
	
	private String descripcion;
	
	private int idCreator;
	
	private String nombreRegistro;
	
	private Alumno alumno;

	public RegistrosMedico() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdRegistros_Medicos() {
		return this.idRegistros_Medicos;
	}

	public void setIdRegistros_Medicos(int idRegistros_Medicos) {
		this.idRegistros_Medicos = idRegistros_Medicos;
	}


	@Temporal(TemporalType.DATE)
	@Column(name="date_of_event")
	public Date getDateOfEvent() {
		return this.dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public int getIdCreator() {
		return this.idCreator;
	}

	public void setIdCreator(int idCreator) {
		this.idCreator = idCreator;
	}


	@Column(name="nombre_registro")
	public String getNombreRegistro() {
		return this.nombreRegistro;
	}

	public void setNombreRegistro(String nombreRegistro) {
		this.nombreRegistro = nombreRegistro;
	}


	//bi-directional many-to-one association to Alumno
	@ManyToOne(fetch=FetchType.LAZY)
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

}