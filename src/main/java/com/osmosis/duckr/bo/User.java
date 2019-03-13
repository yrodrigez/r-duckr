package com.osmosis.duckr.bo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Document
public class User extends org.springframework.security.core.userdetails.User {
	@Id
	public String email;

	public LocalDateTime created;
	public LocalDateTime lastLogin;
	public LocalDateTime lastLogoff;

	public String token = StringUtils.EMPTY;

	public List<Duck> duckList = new LinkedList<>();
	public List<Comment> comments = new LinkedList<>();
	public List<User> follows = new LinkedList<>();

	public User() {
		super("INVALID", "INVALID", new LinkedList<>());
	}

	@JsonCreator
	public User(
		@JsonProperty("username") String username,
		@JsonProperty("password") String password,
		@JsonProperty("email") String email
	) {
		super(username, password, new LinkedList<>());
		this.email = email;
	}

	public User(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public User(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
