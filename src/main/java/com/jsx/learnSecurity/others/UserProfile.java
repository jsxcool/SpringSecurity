package com.jsx.learnSecurity.others;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "profile")
public class UserProfile   // implements GrantedAuthority 
{

	private static final long serialVersionUID = 1L;
	@Id
	private int id;
	@Column
	private String type;

	@ManyToMany(mappedBy = "profiles")
	Set<User> users;
	
	public UserProfile(int id) {
		super();
		this.id = id;
	}

	public UserProfile() {
		super();
	}

	public UserProfile(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
/*
	@Override
	public String getAuthority() {
		return type;
	}
*/
	@Override
	public String toString() {
		return "UserProfile [id=" + id + ", type=" + type + "]";
	}

}

