package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the agenda database table.
 * 
 */
@Entity
@NamedQuery(name="Agenda.findAll", query="SELECT a FROM Agenda a")
public class Agenda implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idAgenda;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	private Usuario usuario;

	//bi-directional many-to-one association to RegistrosAgenda
	@OneToMany(mappedBy="agenda")
	private List<RegistrosAgenda> registrosAgendas;

	public Agenda() {
	}

	public int getIdAgenda() {
		return this.idAgenda;
	}

	public void setIdAgenda(int idAgenda) {
		this.idAgenda = idAgenda;
	}

	public Usuario getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<RegistrosAgenda> getRegistrosAgendas() {
		return this.registrosAgendas;
	}

	public void setRegistrosAgendas(List<RegistrosAgenda> registrosAgendas) {
		this.registrosAgendas = registrosAgendas;
	}

	public RegistrosAgenda addRegistrosAgenda(RegistrosAgenda registrosAgenda) {
		getRegistrosAgendas().add(registrosAgenda);
		registrosAgenda.setAgenda(this);

		return registrosAgenda;
	}

	public RegistrosAgenda removeRegistrosAgenda(RegistrosAgenda registrosAgenda) {
		getRegistrosAgendas().remove(registrosAgenda);
		registrosAgenda.setAgenda(null);

		return registrosAgenda;
	}

}