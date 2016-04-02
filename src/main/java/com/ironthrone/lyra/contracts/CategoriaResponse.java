package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.CategoriaPOJO;

public class CategoriaResponse extends BaseResponse {

	public List<CategoriaPOJO> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<CategoriaPOJO> categorias) {
		this.categorias = categorias;
	}

	public CategoriaPOJO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaPOJO categoria) {
		this.categoria = categoria;
	}

	private List<CategoriaPOJO> categorias;
	private CategoriaPOJO categoria;
	
	public CategoriaResponse()  {
		super();
		// TODO Auto-generated constructor stub
	}
	

	


}
