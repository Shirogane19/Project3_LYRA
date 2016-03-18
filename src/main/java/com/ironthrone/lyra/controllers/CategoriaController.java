package com.ironthrone.lyra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ironthrone.lyra.contracts.CategoriaRequest;
import com.ironthrone.lyra.contracts.CategoriaResponse;
import com.ironthrone.lyra.services.CategoriaServiceInterface;

@RestController
@RequestMapping(value ="rest/protected/categorias")
public class CategoriaController {


		@Autowired private CategoriaServiceInterface categoriaService;
		
		/**
		 * Retorna una lista de todos los Categorias del sistema
		 * @param ur
		 * @return Categoria Response
		 */
		@RequestMapping(value ="/getAll", method = RequestMethod.POST)
		public CategoriaResponse getAll(@RequestBody CategoriaRequest cr){	
				
			CategoriaResponse cresp = new CategoriaResponse();
			cresp.setCode(200);
			cresp.setCodeMessage("Categoria fetch success");
			cresp.setCategorias(categoriaService.getAll(cr));
			return cresp;		
		}
		/**
		 * Retorna una lista de todos los categorias activos o inactivos
		 * @param isActive
		 * @return categoria Response
		 */
		/*@RequestMapping(value ="/getByState", method = RequestMethod.POST)
		public CategoriaResponse getByState(@RequestBody boolean isActive){	
				
			CategoriaResponse cs = new CategoriaResponse();
			cs.setCode(200);
			cs.setCodeMessage("categories fetch success");
			
			if(isActive){
				cs.setCategorias(CategoriaService.getActiveCategorias());
			}else{
				cs.setCategorias(CategoriaService.getInactiveCategorias());
			}
		
			return cs;		
		}*/
		/**
		 * Retorna un unico categoria dado un ID particular.
		 * @param idCategoria
		 * @return CategoriaResponse
		 */
		@RequestMapping(value ="/getCategoria", method = RequestMethod.POST)
		public CategoriaResponse getById(@RequestBody int idCategoria){	
				
			CategoriaResponse cs = new CategoriaResponse();
			cs.setCode(200);
			cs.setCodeMessage("categorias fetch success");
			cs.setCategoria(categoriaService.getCategoriaById(idCategoria));
			return cs;		
		}
		
		/**
		 * Guarda los datos de un categoria, remplaza el Create y Update en el CRUD.
		 * @param cr
		 * @return
		 */
		@RequestMapping(value ="/saveCategoria", method = RequestMethod.POST)
		public CategoriaResponse create(@RequestBody CategoriaRequest cr){	
			
			CategoriaResponse cs = new CategoriaResponse();
			Boolean state = categoriaService.saveCategoria(cr);
		
			if(state){
				cs.setCode(200);
				cs.setCodeMessage("user saved succesfully");
			}
			return cs;
			
		}
		
	


}
