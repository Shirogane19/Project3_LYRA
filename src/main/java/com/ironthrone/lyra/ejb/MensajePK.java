package com.ironthrone.lyra.ejb;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mensajes database table.
 * 
 */
@Embeddable
public class MensajePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;
	private int idMensajes;
	private int chat_idChat;

	public MensajePK() {
	}

	public int getIdMensajes() {
		return this.idMensajes;
	}
	public void setIdMensajes(int idMensajes) {
		this.idMensajes = idMensajes;
	}

	@Column(insertable=false, updatable=false)
	public int getChat_idChat() {
		return this.chat_idChat;
	}
	public void setChat_idChat(int chat_idChat) {
		this.chat_idChat = chat_idChat;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MensajePK)) {
			return false;
		}
		MensajePK castOther = (MensajePK)other;
		return 
			(this.idMensajes == castOther.idMensajes)
			&& (this.chat_idChat == castOther.chat_idChat);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.idMensajes;
		hash = hash * prime + this.chat_idChat;
		
		return hash;
	}
}