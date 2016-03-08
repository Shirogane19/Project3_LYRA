package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.UsuarioPOJO;

public class UsuarioRequest extends BaseRequest{
	
	private UsuarioPOJO usuario;

	public UsuarioPOJO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPOJO usuario) {
		this.usuario = usuario;
	}
	
	@Override
	public String toString() {
		return "Request de Usuario [user=" + usuario + "]";
	}

}
