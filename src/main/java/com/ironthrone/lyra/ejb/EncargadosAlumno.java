package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the encargados_alumno database table.
 * 
 */
@Entity
@Table(name="encargados_alumno")
@NamedQuery(name="EncargadosAlumno.findAll", query="SELECT e FROM EncargadosAlumno e")
public class EncargadosAlumno implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idEncargados_Alumno;
	private Alumno alumno;
	private Usuario usuario;

	public EncargadosAlumno() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdEncargados_Alumno() {
		return this.idEncargados_Alumno;
	}

	public void setIdEncargados_Alumno(int idEncargados_Alumno) {
		this.idEncargados_Alumno = idEncargados_Alumno;
	}


	//bi-directional many-to-one association to Alumno
	@ManyToOne(fetch=FetchType.LAZY)
	public Alumno getAlumno() {
		return this.alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}


	//bi-directional many-to-one association to Usuario
	@ManyToOne(fetch=FetchType.LAZY)
	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}