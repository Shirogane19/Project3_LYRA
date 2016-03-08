package com.ironthrone.lyra.security;

import org.jasypt.util.password.BasicPasswordEncryptor;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Component;

@Component
public class IronPasswordEncryption {

	// for basic encryptions
	
	public String ironEncryption(String password){
		
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		return encryptor.encryptPassword(password);
		
	}
	
	// for strong,slower encryptions
	public String valyrianEncryption(String password){
		
		StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
		return encryptor.encryptPassword(password);
		
	}

	 
	// Even you can configure algorithm
	public String obsidianEncryption(String password){
		
		ConfigurablePasswordEncryptor encryptor = new ConfigurablePasswordEncryptor();
		encryptor.setAlgorithm("SHA-512");
		
		return encryptor.encryptPassword(password);
	}


	// matches password with hash
	
	public boolean clashSteel(String password, String hash){
		
		BasicPasswordEncryptor encryptor = new BasicPasswordEncryptor();
		boolean isOkay = encryptor.checkPassword(password, hash);
		return isOkay;
	}
	 

	
}
