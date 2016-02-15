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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSubscripcion;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_fin")
	private Date fechaFin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	@Column(name="is_active_sub")
	private boolean isActiveSub;

	//bi-directional many-to-one association to Institucion
	@ManyToOne
	private Institucion institucion;

	public Subscripcion() {
	}

	public int getIdSubscripcion() {
		return this.idSubscripcion;
	}

	public void setIdSubscripcion(int idSubscripcion) {
		this.idSubscripcion = idSubscripcion;
	}

	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public boolean getIsActiveSub() {
		return this.isActiveSub;
	}

	public void setIsActiveSub(boolean isActiveSub) {
		this.isActiveSub = isActiveSub;
	}

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

}