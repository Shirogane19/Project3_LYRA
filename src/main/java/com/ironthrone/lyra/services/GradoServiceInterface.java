package com.ironthrone.lyra.services;


import java.util.List;

import com.ironthrone.lyra.contracts.GradoRequest;
import com.ironthrone.lyra.ejb.Grado;
import com.ironthrone.lyra.pojo.GradoPOJO;

/**
 * Interfaz para los servicios de Grado
 * @author jeanpaul
 *
 */
public interface GradoServiceInterface {

	List<GradoPOJO> getAll();
	GradoPOJO getGradoById(int idGrado);
	Grado findById(int idGrado);
	Boolean saveGrado(GradoRequest gr);
}
