package com.ironthrone.lyra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

/**
 *  Interfaz del servicio general, proporciona configuracion del entorno local.
 * @author gerardo parajeles, editado por jeanpaul
 *
 */

@Service("generalService")
public class GeneralService implements GeneralServiceInterface {


	@Autowired private Environment env;

	@Override
	public boolean isLocal() {
		return (env.getProperty("isLocal") != null && env.getProperty("isLocal").equals("true") ) ? true : false;
	}
}
