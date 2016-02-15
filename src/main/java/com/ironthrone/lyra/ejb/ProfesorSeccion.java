package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the profesor_seccion database table.
 * 
 */
@Entity
@Table(name="profesor_seccion")
@NamedQuery(name="ProfesorSeccion.findAll", query="SELECT p FROM ProfesorSeccion p")
public class ProfesorSeccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idProfesor_Seccion;

	//bi-directional many-to-one association to Seccion
	@ManyToOne
	private Seccion seccion;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	public ProfesorSeccion() {
	}

	public int getIdProfesor_Seccion() {
		return this.idProfesor_Seccion;
	}

	public void setIdProfesor_Seccion(int idProfesor_Seccion) {
		this.idProfesor_Seccion = idProfesor_Seccion;
	}

	public Seccion getSeccion() {
		return this.seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}