package com.ironthrone.lyra.security;

import org.springframework.mail.MailException;
import org.springframework.stereotype.Component;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MediaType;

/**
 * Clase que permite al sistema enviar correos a sus usuarios medinate mailgun API
 * @author jeanpaul
 *
 */
@Component
public class RavenMail {
	
	private static final String mailgunApiKey = "key-014f04ae9199de2e8cd44567b1357486";
	private static final String mailgunHost = "sandbox3a54c3e69d7c4941ad4fc59e55291641.mailgun.org";
	private final static String lyra_mail = "lyra.recover@gmail.com";
	

	
	/**
	 * Manda un correo a un usuario por medio del API de mailgun.
	 * @param email del destinatario
	 * @param name  nombre del usuario
	 * @param lastname apellido del usuario
	 * @param message contenido del correo
	 */
	public void SendRavenMessage(String email, String name, String lastname, String message) {
		
	    try {

	    Client client = Client.create();
	    client.addFilter(new HTTPBasicAuthFilter("api", mailgunApiKey));
	    
	    WebResource webResource = client.resource("https://api.mailgun.net/v2/" + mailgunHost +  "/messages");

	    
	    MultivaluedMapImpl formData = new MultivaluedMapImpl();
	    
        formData.add("from", lyra_mail);
        formData.add("to", email);
        formData.add("subject", "Credenciales");
        formData.add("html", "Estimado " + name + " "
						                + lastname + ","
						                + message);   
        
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        String output = clientResponse.getEntity(String.class);
        System.out.println("MAIL: " + output);
        
	    }catch (MailException ex) {
	        // simply log it and go on...
	        System.err.println(ex.getMessage());
	    }
	    
	}

}