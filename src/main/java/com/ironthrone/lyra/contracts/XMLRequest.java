package com.ironthrone.lyra.contracts;

public class XMLRequest extends BaseRequest {
	
	private String xmlPath;
	private int idInstitucion;

	public XMLRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getXmlPath() {
		return xmlPath;
	}

	public void setXmlPath(String xmlPath) {
		this.xmlPath = xmlPath;
	}

	public int getIdInstitucion() {
		return idInstitucion;
	}

	public void setIdInstitucion(int idInstitucion) {
		this.idInstitucion = idInstitucion;
	}

	@Override
	public String toString() {
		return "Request de Usuario [Path del .xml = " + xmlPath + "]";
	}
	
}
