package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.CategoriaPOJO;


public class CategoriaRequest extends BaseRequest {

	public CategoriaRequest(){
		super();
	}
	private CategoriaPOJO categoria;

	public CategoriaPOJO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPOJO Categoria) {
		this.categoria = Categoria;
	}
	
	@Override
	public String toString() {
		return "Request de Categoria [categoria=" + categoria + "]";
	}
}
