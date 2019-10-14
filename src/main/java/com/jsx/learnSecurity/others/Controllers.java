package com.jsx.learnSecurity.others;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller  // controller return template View
// @RestController return body(json)
public class Controllers {

	@Autowired
	UserDao dao;
	
	@GetMapping("/userRegister")
	String registerForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/userRegister")
	String register(@ModelAttribute User u) {
		u.getProfiles().add(new UserProfile(1, "user"));
		dao.save(u);
		return "login";
	}
	
}
