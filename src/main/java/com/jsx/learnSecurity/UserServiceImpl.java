package com.jsx.learnSecurity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.jsx.learnSecurity.others.User;
import com.jsx.learnSecurity.others.UserDao;
import com.jsx.learnSecurity.others.UserProfile;

@Service   // this annotation is necessary
//*** UserDetailsService is a core interface, which is used to retrieve user's
//    authentication and authorization information
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserDao ud;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ud.findByUsername(username);
		
		if(user == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
	
		// construct User, do authentication
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
		builder.password(user.getPassword());
		
		// add authorities
		Set<UserProfile> profiles = user.getProfiles();
		String[] roles = new String[profiles.size()];
		Iterator<UserProfile> itr=profiles.iterator();
		for(int i=0; i<profiles.size() && itr.hasNext(); i++)
			roles[i] = itr.next().getType();
		builder.roles(roles);
		
		// build as UserDetails
		return builder.build();
		
	}

}