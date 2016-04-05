package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
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
	private Institucion institucion;
	private List<Materia> materias;
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


	//bi-directional many-to-one association to Institucion
	@ManyToOne(fetch=FetchType.LAZY)
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}


	//bi-directional many-to-many association to Materia
	@ManyToMany(fetch = FetchType.LAZY,cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
		name="materias_grado"
		, joinColumns={
			@JoinColumn(name="Grado_idGrado")
			}
		, inverseJoinColumns={
			@JoinColumn(name="Materia_idMateria")
			}
		)
	public List<Materia> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<Materia> materias) {
		this.materias = materias;
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