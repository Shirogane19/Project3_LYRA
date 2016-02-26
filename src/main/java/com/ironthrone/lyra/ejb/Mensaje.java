package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the mensajes database table.
 * 
 */
@Entity
@Table(name="mensajes")
@NamedQuery(name="Mensaje.findAll", query="SELECT m FROM Mensaje m")
public class Mensaje implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int idMensajes;
	private Date fecha;
	private String mensaje;
	private Chat chat;

	public Mensaje() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdMensajes() {
		return idMensajes;
	}


	public void setIdMensajes(int idMensajes) {
		this.idMensajes = idMensajes;
	}


	@Temporal(TemporalType.TIMESTAMP)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}


	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	//bi-directional many-to-one association to Chat
	@ManyToOne(fetch=FetchType.LAZY)
	public Chat getChat() {
		return this.chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}






}