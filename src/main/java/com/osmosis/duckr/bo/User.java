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
	public String email = StringUtils.EMPTY;

	public LocalDateTime created;
	public LocalDateTime lastLogin;
	public LocalDateTime lastLogoff;

	public String token = StringUtils.EMPTY;

	public List<Duck> duckList = new LinkedList<>();
	public List<Comment> comments = new LinkedList<>();
	public List<User> follows = new LinkedList<>();

	@JsonCreator
	public User(
		@JsonProperty("username") final String username,
		@JsonProperty("password")	final String password,
		@JsonProperty("email") final String email
	) {
		super(username, password, new LinkedList<>());
		this.email = email;

	}

	public User(final String username, final String password, final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public User(final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
