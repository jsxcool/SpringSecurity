package com.jsx.learnSecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsx.learnSecurity.others.User;
import com.jsx.learnSecurity.others.UserDao;

@Service   // this annotation is necessary
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserDao ud;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(username);
		System.out.println(ud);
		User user = ud.findByUsername(username);
		if(user == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		return user;
		
	}

}