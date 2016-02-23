package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the subscripcion database table.
 * 
 */
@Entity
@NamedQuery(name="Subscripcion.findAll", query="SELECT s FROM Subscripcion s")
public class Subscripcion implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idSubscripcion;
	private Date fechaFin;
	private Date fechaInicio;
	private boolean isActiveSub;
	private Institucion institucion;

	public Subscripcion() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdSubscripcion() {
		return this.idSubscripcion;
	}

	public void setIdSubscripcion(int idSubscripcion) {
		this.idSubscripcion = idSubscripcion;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}


	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}


	@Column(name="is_active_sub")
	public boolean getIsActiveSub() {
		return this.isActiveSub;
	}

	public void setIsActiveSub(boolean isActiveSub) {
		this.isActiveSub = isActiveSub;
	}


	//bi-directional many-to-one association to Institucion
	@ManyToOne
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

}