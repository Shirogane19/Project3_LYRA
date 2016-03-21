package com.ironthrone.lyra.contracts;

import com.ironthrone.lyra.pojo.SubscripcionPOJO;

/**
 * Clase request, contrato que almacena la solicitudes relacionada con subscripcion
 * @author Randall
 *
 */
public class SubscripcionRequest extends BaseRequest {
	
	private SubscripcionPOJO subscripcion;

	public SubscripcionPOJO getSubscripcion() {
		return subscripcion;
	}

	public void setSubscripcion(SubscripcionPOJO subscripcion) {
		this.subscripcion = subscripcion;
	}
	
	@Override
	public String toString() {
		return "Request de Subscripción [subscripción=" + subscripcion + "]";
	}

}
