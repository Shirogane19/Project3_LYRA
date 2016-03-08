package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the materia database table.
 * 
 */
@Entity
@NamedQuery(name="Materia.findAll", query="SELECT m FROM Materia m")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idMateria;
	private boolean isActiveMat;
	private String nombre;
	private Institucion institucion;
	private List<MateriasGrado> materiasGrados;
	private List<MateriasProfesor> materiasProfesors;

	public Materia() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdMateria() {
		return this.idMateria;
	}

	public void setIdMateria(int idMateria) {
		this.idMateria = idMateria;
	}


	@Column(name="is_active_mat")
	public boolean getIsActiveMat() {
		return this.isActiveMat;
	}

	public void setIsActiveMat(boolean isActiveMat) {
		this.isActiveMat = isActiveMat;
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


	//bi-directional many-to-one association to MateriasGrado
	@OneToMany(mappedBy="materia")
	public List<MateriasGrado> getMateriasGrados() {
		return this.materiasGrados;
	}

	public void setMateriasGrados(List<MateriasGrado> materiasGrados) {
		this.materiasGrados = materiasGrados;
	}

	public MateriasGrado addMateriasGrado(MateriasGrado materiasGrado) {
		getMateriasGrados().add(materiasGrado);
		materiasGrado.setMateria(this);

		return materiasGrado;
	}

	public MateriasGrado removeMateriasGrado(MateriasGrado materiasGrado) {
		getMateriasGrados().remove(materiasGrado);
		materiasGrado.setMateria(null);

		return materiasGrado;
	}


	//bi-directional many-to-one association to MateriasProfesor
	@OneToMany(mappedBy="materia")
	public List<MateriasProfesor> getMateriasProfesors() {
		return this.materiasProfesors;
	}

	public void setMateriasProfesors(List<MateriasProfesor> materiasProfesors) {
		this.materiasProfesors = materiasProfesors;
	}

	public MateriasProfesor addMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().add(materiasProfesor);
		materiasProfesor.setMateria(this);

		return materiasProfesor;
	}

	public MateriasProfesor removeMateriasProfesor(MateriasProfesor materiasProfesor) {
		getMateriasProfesors().remove(materiasProfesor);
		materiasProfesor.setMateria(null);

		return materiasProfesor;
	}

}