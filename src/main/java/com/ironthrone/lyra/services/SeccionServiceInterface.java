package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.SeccionRequest;
import com.ironthrone.lyra.ejb.Seccion;
import com.ironthrone.lyra.pojo.SeccionPOJO;

public interface SeccionServiceInterface {
	List<SeccionPOJO> getAll(SeccionRequest ur);
	List<SeccionPOJO> getActiveSecciones();
	List<SeccionPOJO> getInactiveSecciones();
	SeccionPOJO getSeccionById(int idSeccion);
	Seccion findById(int idSeccion);
	Boolean saveSeccion(SeccionRequest ur);
	SeccionPOJO getProfes(int idSeccion);
}
