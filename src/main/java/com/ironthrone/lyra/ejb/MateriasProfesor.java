package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the materias_profesor database table.
 * 
 */
@Entity
@Table(name="materias_profesor")
@NamedQuery(name="MateriasProfesor.findAll", query="SELECT m FROM MateriasProfesor m")
public class MateriasProfesor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMaterias_Profesor;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	private Materia materia;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public MateriasProfesor() {
	}

	public int getIdMaterias_Profesor() {
		return this.idMaterias_Profesor;
	}

	public void setIdMaterias_Profesor(int idMaterias_Profesor) {
		this.idMaterias_Profesor = idMaterias_Profesor;
	}

	public Materia getMateria() {
		return this.materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}