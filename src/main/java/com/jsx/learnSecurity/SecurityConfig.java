package com.jsx.learnSecurity;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserServiceImpl usi;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            	// url '/', '/home' are configured not require any authentication
                .antMatchers("/", "/home", "/userRegister").permitAll()
                
                // authority management
                .antMatchers("/authority").access("hasRole('user')")
                //.antMatchers("/authority").hasRole("user")
             
                // any other paths must be authenticated 
                // (go to login -- authorize by spring)
                .anyRequest().authenticated()
                .and()
             // configure login page to do authentication
            .formLogin()
                .loginPage("/login")
                .permitAll()    // anybody can access login, register
                .usernameParameter("username").passwordParameter("password") // same as default
                .and()
            .logout()
                .permitAll();
    }

    /*
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
             User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
    */
    
    
    @Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	// AuthenticationManager gets the info of the exact User in DB
		auth.userDetailsService(usi).passwordEncoder(bCriptEncoder());
		// authorize password by bCriptEncoder
    }

	@Bean
	public PasswordEncoder bCriptEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder(11);
		return encoder;
	}
	
	/*
    // autowire means config globally
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(uds);
	    //.passwordEncoder(passwordEncoder());
	}
	*/
	

}
