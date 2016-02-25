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

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idGrado;

	private String descripcion;

	@Column(name="is_active_gr")
	private boolean isActiveGr;

	private String nombre;

	@Temporal(TemporalType.DATE)
	private Date year;

	//bi-directional many-to-one association to Institucion
	@ManyToOne
	private Institucion institucion;

	//bi-directional many-to-one association to MateriasGrado
	@OneToMany(mappedBy="grado")
	private List<MateriasGrado> materiasGrados;

	//bi-directional many-to-one association to Seccion
	@OneToMany(mappedBy="grado")
	private List<Seccion> seccions;

	public Grado() {
	}

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

	public Date getYear() {
		return this.year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}

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