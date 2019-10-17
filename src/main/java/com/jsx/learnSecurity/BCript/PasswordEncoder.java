package com.jsx.learnSecurity.BCript;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordEncoder {
	
	static public void main(String[] args) {
		String hashLayer = BCrypt.gensalt(4);
		System.out.println(hashLayer);
		String pw_hash = BCrypt.hashpw("123", hashLayer);
		System.out.println(pw_hash);
		
		if(BCrypt.checkpw("123", "$2a$04$4HfEjhoOKspGpvqFx9up6ePOhzRWKK94UOfxwAl7wQ25QxfN2GrmK"))
			System.out.println("It matches");
		else
		    System.out.println("It does not match");
		
	} 

}
