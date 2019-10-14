package com.jsx.learnSecurity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.jsx.learnSecurity.others.UserDao;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearnSecurityApplicationTests {

	@Autowired
	UserDao ud;
	
	@Test
	public void contextLoads() {
		System.out.println(ud);
		System.out.println(ud.findById(1));
		System.out.println(ud.findByUsername("jsx"));
	}

}
