package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the materias_grado database table.
 * 
 */
@Entity
@Table(name="materias_grado")
@NamedQuery(name="MateriasGrado.findAll", query="SELECT m FROM MateriasGrado m")
public class MateriasGrado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMaterias_Grado;

	//bi-directional many-to-one association to Grado
	@ManyToOne
	private Grado grado;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	private Materia materia;

	public MateriasGrado() {
	}

	public int getIdMaterias_Grado() {
		return this.idMaterias_Grado;
	}

	public void setIdMaterias_Grado(int idMaterias_Grado) {
		this.idMaterias_Grado = idMaterias_Grado;
	}

	public Grado getGrado() {
		return this.grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

}