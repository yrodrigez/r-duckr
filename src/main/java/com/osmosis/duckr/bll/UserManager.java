package com.osmosis.duckr.bll;

import com.osmosis.duckr.bo.User;
import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserManager {

	private final UserRepository repository;

	@Autowired
	public UserManager(final UserRepository repository) {
		this.repository = repository;
	}

	public User registerUser(final User user) {
		if (isUserRegistered(user)) {
			throw new RuntimeException("This username is already taken");
		}

		return this.repository.save(new User(user.getUsername(), user.getPassword(), user.email));
	}

	private boolean isUserRegistered(final User user) {
			Optional<User> byUsername = this.repository.findByUsername(user.getUsername());
			Optional<User> byEmail = this.repository.findByEmail(user.email);

			return byUsername.isPresent() || byEmail.isPresent();
	}
}
