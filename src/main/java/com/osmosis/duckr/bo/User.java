package com.osmosis.duckr.bo;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Document
public class User implements BO {
	@Id
	public String email;
	public String password;
	public String username;

	public LocalDateTime created;
	public LocalDateTime lastLogin;
	public LocalDateTime lastLogoff;

	public List<Duck> duckList = new LinkedList<>();
	public List<Comment> comments = new LinkedList<>();
	public List<User> follows = new LinkedList<>();

	public List<? extends GrantedAuthority> authorities = new LinkedList<>();


}
