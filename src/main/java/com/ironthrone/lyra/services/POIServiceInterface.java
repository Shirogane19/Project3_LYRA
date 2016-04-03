package com.ironthrone.lyra.services;

import java.io.IOException;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import com.ironthrone.lyra.pojo.AlumnoPOJO;
import com.ironthrone.lyra.pojo.UsuarioPOJO;

/**
 * Interfaz que utiliza Apache POI para retornar la lista de objetos pojo
 * del excel.
 * @author jeanpaul
 *
 */
public interface POIServiceInterface {
	

	void setEnviroment(String _file)throws IllegalStateException, IOException, InvalidFormatException;
	List<UsuarioPOJO> getUsers(List<UsuarioPOJO> users);
	List<AlumnoPOJO> getAlumnos (List<AlumnoPOJO> students);


}
