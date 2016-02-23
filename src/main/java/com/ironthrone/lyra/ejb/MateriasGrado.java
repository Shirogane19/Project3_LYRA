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
	private int idMaterias_Grado;
	private Grado grado;
	private Materia materia;

	public MateriasGrado() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdMaterias_Grado() {
		return this.idMaterias_Grado;
	}

	public void setIdMaterias_Grado(int idMaterias_Grado) {
		this.idMaterias_Grado = idMaterias_Grado;
	}


	//bi-directional many-to-one association to Grado
	@ManyToOne
	public Grado getGrado() {
		return this.grado;
	}

	public void setGrado(Grado grado) {
		this.grado = grado;
	}


	//bi-directional many-to-one association to Materia
	@ManyToOne
	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

}