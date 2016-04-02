package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.UsuarioPOJO;

public class UsuarioResponse extends BaseResponse {

	private List<UsuarioPOJO> usuarios;
	private UsuarioPOJO usuario;
	
	
	public UsuarioResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<UsuarioPOJO> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<UsuarioPOJO> usuarios) {
		this.usuarios = usuarios;
	}

	public UsuarioPOJO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioPOJO usuario) {
		this.usuario = usuario;
	}

	
}
