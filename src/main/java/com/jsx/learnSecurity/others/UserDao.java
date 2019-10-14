package com.jsx.learnSecurity.others;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserDao extends JpaRepository<User, Integer> {
	User findByUsername(String username);
	
}


