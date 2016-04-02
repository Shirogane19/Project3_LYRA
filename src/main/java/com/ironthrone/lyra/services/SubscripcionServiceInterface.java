package com.ironthrone.lyra.services;

import java.util.List;

import com.ironthrone.lyra.contracts.SubscripcionRequest;
import com.ironthrone.lyra.ejb.Subscripcion;
import com.ironthrone.lyra.pojo.SubscripcionPOJO;

public interface SubscripcionServiceInterface {

	List<SubscripcionPOJO> getAll();
	Boolean saveSubscripcion(SubscripcionRequest subscripcionRequest);
	SubscripcionPOJO getSubscripcionById(int idSubscripcion);
	Subscripcion findById(int idSubscripcion);
	List<SubscripcionPOJO> getAllByActive();
	void revisarVencimientos();
	SubscripcionPOJO renovarSubscripcion(SubscripcionRequest subscripcionRequest);
}
