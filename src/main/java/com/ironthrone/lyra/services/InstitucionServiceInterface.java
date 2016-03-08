package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.InstitucionRequest;
import com.ironthrone.lyra.ejb.Institucion;
import com.ironthrone.lyra.pojo.InstitucionPOJO;


public interface InstitucionServiceInterface {

	List<InstitucionPOJO> getAll();
	InstitucionPOJO saveInstitucion(InstitucionRequest institucionRequest);
	InstitucionPOJO getInstitucionById(int idInstitucion);
	Institucion findById(int idInstitucion);
}
