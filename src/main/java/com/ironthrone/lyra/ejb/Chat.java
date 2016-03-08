package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the chat database table.
 * 
 */
@Entity
@NamedQuery(name="Chat.findAll", query="SELECT c FROM Chat c")
public class Chat implements Serializable {
	private static final long serialVersionUID = 1L;
	private int idChat;
	private String nombre;
	private List<Usuario> usuarios;
	private List<Mensaje> mensajes;

	public Chat() {
	}


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getIdChat() {
		return this.idChat;
	}

	public void setIdChat(int idChat) {
		this.idChat = idChat;
	}


	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	//bi-directional many-to-many association to Usuario
	@ManyToMany(mappedBy="chats")
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	//bi-directional many-to-one association to Mensaje
	@OneToMany(mappedBy="chat")
	public List<Mensaje> getMensajes() {
		return this.mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje addMensaje(Mensaje mensaje) {
		getMensajes().add(mensaje);
		mensaje.setChat(this);

		return mensaje;
	}

	public Mensaje removeMensaje(Mensaje mensaje) {
		getMensajes().remove(mensaje);
		mensaje.setChat(null);

		return mensaje;
	}

}