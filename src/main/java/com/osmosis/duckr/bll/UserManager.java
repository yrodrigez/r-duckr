package com.osmosis.duckr.bll;

import com.osmosis.duckr.bo.BO;
import com.osmosis.duckr.bo.Error;
import com.osmosis.duckr.bo.User;
import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserManager {

	private final UserRepository repository;

	@Autowired
	public UserManager(final UserRepository repository) {
		this.repository = repository;
	}

	public BO registerUser(final User user) {
		if (isUserRegistered(user)) {
			Error error = new Error();
			error.code = "404";
			error.message = "The user with email and username already exists";

			return error;
		}

		List<GrantedAuthority> authorityList = new LinkedList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));

		final User newUser = new User();
		newUser.username = user.username;
		newUser.password = user.password;
		newUser.email = user.email;
		newUser.created = LocalDateTime.now();
		newUser.lastLogin = LocalDateTime.now();
		newUser.lastLogoff = LocalDateTime.now();
		newUser.comments = new LinkedList<>();
		newUser.follows = new LinkedList<>();
		newUser.duckList = new LinkedList<>();
		newUser.authorities = authorityList;


		return this.repository.save(newUser);
	}

	private boolean isUserRegistered(final User user) {
		Optional<User> byUsername = this.repository.findByUsername(user.username);
		Optional<User> byEmail = this.repository.findByEmail(user.email);

		return byUsername.isPresent() || byEmail.isPresent();
	}
}
