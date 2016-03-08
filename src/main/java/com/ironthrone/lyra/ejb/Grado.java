package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the grado database table.
 * 
 */
@Entity
@NamedQuery(name="Grado.findAll", query="SELECT g FROM Grado g")
public class Grado implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idGrado;
	private String descripcion;
	private boolean isActiveGr;
	private String nombre;
	private Date year;
	private Institucion institucion;
	private List<MateriasGrado> materiasGrados;
	private List<Seccion> seccions;

	public Grado() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdGrado() {
		return this.idGrado;
	}

	public void setIdGrado(int idGrado) {
		this.idGrado = idGrado;
	}


	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	@Column(name="is_active_gr")
	public boolean getIsActiveGr() {
		return this.isActiveGr;
	}

	public void setIsActiveGr(boolean isActiveGr) {
		this.isActiveGr = isActiveGr;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	@Temporal(TemporalType.DATE)
	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}


	//bi-directional many-to-one association to Institucion
	@ManyToOne(fetch=FetchType.LAZY)
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}


	//bi-directional many-to-one association to MateriasGrado
	@OneToMany(mappedBy="grado")
	public List<MateriasGrado> getMateriasGrados() {
		return this.materiasGrados;
	}

	public void setMateriasGrados(List<MateriasGrado> materiasGrados) {
		this.materiasGrados = materiasGrados;
	}

	public MateriasGrado addMateriasGrado(MateriasGrado materiasGrado) {
		getMateriasGrados().add(materiasGrado);
		materiasGrado.setGrado(this);

		return materiasGrado;
	}

	public MateriasGrado removeMateriasGrado(MateriasGrado materiasGrado) {
		getMateriasGrados().remove(materiasGrado);
		materiasGrado.setGrado(null);

		return materiasGrado;
	}


	//bi-directional many-to-one association to Seccion
	@OneToMany(mappedBy="grado")
	public List<Seccion> getSeccions() {
		return this.seccions;
	}

	public void setSeccions(List<Seccion> seccions) {
		this.seccions = seccions;
	}

	public Seccion addSeccion(Seccion seccion) {
		getSeccions().add(seccion);
		seccion.setGrado(this);

		return seccion;
	}

	public Seccion removeSeccion(Seccion seccion) {
		getSeccions().remove(seccion);
		seccion.setGrado(null);

		return seccion;
	}

}