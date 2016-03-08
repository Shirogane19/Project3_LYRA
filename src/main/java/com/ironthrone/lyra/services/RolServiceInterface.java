package com.ironthrone.lyra.services;

import java.util.List;
import com.ironthrone.lyra.pojo.RolPOJO;

/**
 * Declara los servicios de rol.
 * @author jeanpaul
 *
 */
public interface RolServiceInterface {
	
	List<RolPOJO> getAll();
	List<RolPOJO> findByUsuario(int idUsuario);
	List<RolPOJO> findByTarea(int idTarea);
}
