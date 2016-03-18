package com.ironthrone.lyra.contracts;

import java.util.List;

import com.ironthrone.lyra.pojo.SubscripcionPOJO;

/**
 * Clase de tipo response, retorna un contrato con las subscripciones solicitadas
 * @author Randall
 *
 */
public class SubscripcionResponse extends BaseResponse{
	
	private List<SubscripcionPOJO> subscripciones;
	private SubscripcionPOJO subscripcion;
	
	public SubscripcionResponse(){
		super();
	}

	public List<SubscripcionPOJO> getSubscripciones() {
		return subscripciones;
	}

	public void setSubscripciones(List<SubscripcionPOJO> subscripciones) {
		this.subscripciones = subscripciones;
	}

	public SubscripcionPOJO getSubscripcion() {
		return subscripcion;
	}

	public void setSubscripcion(SubscripcionPOJO subscripcion) {
		this.subscripcion = subscripcion;
	}
	
	
	

}
