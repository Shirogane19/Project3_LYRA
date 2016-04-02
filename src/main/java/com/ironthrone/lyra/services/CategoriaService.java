package com.ironthrone.lyra.services;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.ironthrone.lyra.contracts.CategoriaRequest;

import com.ironthrone.lyra.ejb.Categoria;


import com.ironthrone.lyra.pojo.CategoriaPOJO;

import com.ironthrone.lyra.repositories.CategoriaRepository;


@Service
public class CategoriaService implements CategoriaServiceInterface{

	@Autowired private CategoriaRepository categoriaRepository;
	
	/**
	 * Genera POJOs a partir de una lista EJB.
	 * @param Categorias representa una lista de categorias tipo ejb
	 * @return CategoriaInterfaceCategorias, lista de categorias POJO.
	 */
	private List<CategoriaPOJO> generateCategoriaDtos(List<Categoria> categorias){
		
		List<CategoriaPOJO> ciCategorias = new ArrayList<CategoriaPOJO>();
		
		categorias.stream().forEach(c -> {
			CategoriaPOJO dto = new CategoriaPOJO();
			dto.setIdCategoria(c.getIdCategoria());
			dto.setNombreCategoria(c.getNombreCategoria());
			dto.setDescripcionCategoria(c.getDescripcionCategoria());
			dto.setTareas(null);
			dto.setActiveCat(c.getIsActiveCat());
			ciCategorias.add(dto);
		});	
		
		return ciCategorias;
	};
	/**
	 * Retorna una lista de Categorias.
	 * @param Categoria request de la capa frontend.
	 * @return lista de Categorias POJO
	 */
	@Override
	@Transactional
	public List<CategoriaPOJO> getAll(CategoriaRequest cr) {

		List<Categoria> categorias =  categoriaRepository.findAll();
		return generateCategoriaDtos(categorias);
	}
	/**
	 * Retorna los detalles de un Categoria..
	 * @param idCategoria, identificador unico de Categoria.
	 * @return Categoria de tipo CategoriaPOJO.
	 */
	@Override
	@Transactional
	public CategoriaPOJO getCategoriaById(int idCategoria) {

		Categoria c = categoriaRepository.findOne(idCategoria);
		CategoriaPOJO dto = new CategoriaPOJO();
		
		dto.setIdCategoria(c.getIdCategoria());
		dto.setNombreCategoria(c.getNombreCategoria());
		dto.setDescripcionCategoria(c.getDescripcionCategoria());
		dto.setTareas(null);
		dto.setActiveCat(c.getIsActiveCat());

	
		return dto;
	}
	/**
	 * Retorna una lista de categorias activos.
	 * @return Lista de Categoria tipo POJO
	 */
	/*@Override
	@Transactional
	public List<CategoriaPOJO> getActiveCategorias() {

		List<Categoria> categorias =  CategoriaRepository.findByisActiveCat();
		
		return generateCategoriaDtos(categorias);
	}
	
	*//**
	 * Retorna una lista de categorias inactivos.
	 * @return Lista de Categoria tipo POJO
	 *//*
	@Override
	public List<CategoriaPOJO> getInactiveCategorias() {
		
		List<Categoria> categorias =  CategoriaRepository.findByisActiveCatFalse();
		
		return generateCategoriaDtos(categorias);
	}
	*//**
	 * Retorna un unico Categoria por ID.
	 * @param idCategoria, identificador unico de Categoria.
	 * @return Categoria de tipo CategoriaEJB.
	 */
	@Override
	public Categoria findById(int idCategoria) {

		return categoriaRepository.findOne(idCategoria);
	}
	/**
	 * Guarda los datos de un Categoria.
	 * @param Categoria request de la capa frontend.
	 * @return booleano, true = success, false = error.
	 */
	@Override
	public Boolean saveCategoria(CategoriaRequest cr) {
		Categoria newCategoria = new Categoria();
		Categoria nCategoria = null;

		BeanUtils.copyProperties(cr.getCategoria(), newCategoria);	
		newCategoria.setIsActiveCat(cr.getCategoria().isActiveCat());

			
		if(cr.getCategoria().getIdCategoria() <= -1){		
			nCategoria = categoriaRepository.save(newCategoria);
		 
		}else{		
			Categoria oldCategoria = findById(newCategoria.getIdCategoria());
			BeanUtils.copyProperties(newCategoria, oldCategoria);
			oldCategoria.setIsActiveCat(newCategoria.getIsActiveCat());
			nCategoria = categoriaRepository.save(oldCategoria);	
		}
		return (nCategoria == null) ? false : true;
	}

}
