package com.jsx.learnSecurity;

import java.util.ArrayList;
import java.util.List;

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
public class UserServiceImpl implements UserDetailsService {

	@Autowired
	UserDao ud;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = ud.findByUsername(username);
		System.out.println(user);
		if(user == null) {
			throw new UsernameNotFoundException("User " + username + " was not found in the database");
		}
		
		UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(username);
		builder.password(user.getPassword());
		List<UserProfile> profiles = new ArrayList<>(user.getProfiles());
		String[] roles = new String[profiles.size()];
		for(int i=0; i<profiles.size(); i++)
			roles[i] = profiles.get(i).getType();
		builder.roles(roles);
		
		//return user;
		return builder.build();
		
	}

}