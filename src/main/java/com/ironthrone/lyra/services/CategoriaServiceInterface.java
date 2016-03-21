package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.CategoriaRequest;
import com.ironthrone.lyra.ejb.Categoria;
import com.ironthrone.lyra.pojo.CategoriaPOJO;

public interface CategoriaServiceInterface {
	
	List<CategoriaPOJO> getAll(CategoriaRequest ur);
	/*List<CategoriaPOJO> getActiveCategorias();
	List<CategoriaPOJO> getInactiveCategorias();*/
	CategoriaPOJO getCategoriaById(int idCategoria);
	Categoria findById(int idCategoria);
	Boolean saveCategoria(CategoriaRequest ur);

}
