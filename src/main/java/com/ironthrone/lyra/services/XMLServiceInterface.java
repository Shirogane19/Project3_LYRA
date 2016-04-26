package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.XMLResponse;
import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;


/**
 * Interfaz de servicio de XML
 * @author jeanpaul
 *
 */
public interface XMLServiceInterface {
	
	//Boolean bulkUpload(int idInstitucion, MultipartFile file);
	XMLResponse bulkUpload(int idInstitucion, String file);
	Boolean insertUsers(List<UsuarioPOJO> users);
	Boolean insertAlumnos (List <AlumnoPOJO> students);
	
	

}
