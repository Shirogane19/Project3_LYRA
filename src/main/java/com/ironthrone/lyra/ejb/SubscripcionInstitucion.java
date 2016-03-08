package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the subscripcion_institucion database table.
 * 
 */
@Entity
@Table(name="subscripcion_institucion")
@NamedQuery(name="SubscripcionInstitucion.findAll", query="SELECT s FROM SubscripcionInstitucion s")
public class SubscripcionInstitucion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idSubscripcionInstitucion;

	//bi-directional many-to-one association to Institucion
	@ManyToOne
	private Institucion institucion;

	//bi-directional many-to-one association to Subscripcion
	@ManyToOne
	private Subscripcion subscripcion;

	public SubscripcionInstitucion() {
	}

	public int getIdSubscripcionInstitucion() {
		return this.idSubscripcionInstitucion;
	}

	public void setIdSubscripcionInstitucion(int idSubscripcionInstitucion) {
		this.idSubscripcionInstitucion = idSubscripcionInstitucion;
	}

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

	public Subscripcion getSubscripcion() {
		return this.subscripcion;
	}

	public void setSubscripcion(Subscripcion subscripcion) {
		this.subscripcion = subscripcion;
	}

}