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
	private List<Grado> grados;
	private Institucion institucion;
	private List<Usuario> usuarios;

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


	//bi-directional many-to-many association to Grado
	@ManyToMany(mappedBy="materias")
	public List<Grado> getGrados() {
		return this.grados;
	}

	public void setGrados(List<Grado> grados) {
		this.grados = grados;
	}


	//bi-directional many-to-one association to Institucion
	@ManyToOne(fetch=FetchType.LAZY)
	public Institucion getInstitucion() {
		return this.institucion;
	}

	public void setInstitucion(Institucion institucion) {
		this.institucion = institucion;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="materias")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

}