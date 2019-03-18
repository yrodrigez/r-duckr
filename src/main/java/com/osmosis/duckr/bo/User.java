package com.osmosis.duckr.bo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Document("users")
public class User implements BO, Authentication, Principal, Serializable, UserDetails {

	public static final String ROLE_USER = "ROLE_USER";
	public static final String ROLE_PUBLISHER = "ROLE_PUBLISHER";
	public static final String ROLE_COMMENTER = "ROLE_COMMENTER";

	@Id
	public String username;
	public String password;

	public String email;

	public LocalDateTime created;
	public LocalDateTime lastLogin;
	public LocalDateTime lastLogoff;

	@DBRef
	public List<Duck> duckList = new LinkedList<>();
	@DBRef
	public List<Duck> starred = new LinkedList<>();
	@DBRef
	public List<Comment> comments = new LinkedList<>();
	@DBRef
	public List<User> follows = new LinkedList<>();

	public List<? extends GrantedAuthority> authorities = new LinkedList<>();
	private boolean isAuth;


	public static class UserCredentials implements CredentialsContainer{
		public String username;
		public String password;

		@Override
		public void eraseCredentials() {
			username = password = "";
		}
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public Object getCredentials() {
		UserCredentials credentials = new UserCredentials();
		credentials.username = this.username;
		credentials.password = this.password;

		return credentials;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}

	@Override
	public boolean isAuthenticated() {
		return this.isAuth;
	}

	@Override
	public void setAuthenticated(final boolean isAuthenticated) throws IllegalArgumentException {
		this.isAuth = isAuthenticated;
	}

	@Override
	public String getName() {
		return this.username;
	}
}
