package com.ironthrone.lyra.services;

import java.util.List;


import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.GradoPOJO;
import com.ironthrone.lyra.pojo.MateriaPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;


/**
 * Interfaz de servicio de XML
 * @author jeanpaul
 *
 */
public interface XMLServiceInterface {
	
	//Boolean bulkUpload(int idInstitucion, MultipartFile file);
	Boolean bulkUpload(int idInstitucion, String file);
	Boolean insertUsers(List<UsuarioPOJO> users);
	Boolean insertGrados (List<GradoPOJO> grados);
	Boolean insertMaterias (List<MateriaPOJO> materias);
	Boolean insertAlumnos (List <AlumnoPOJO> students);
	
	

}
