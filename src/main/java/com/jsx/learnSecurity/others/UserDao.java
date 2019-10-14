package com.jsx.learnSecurity.others;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	
}


