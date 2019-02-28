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

		return this.repository.save(user);
	}

	private boolean isUserRegistered(final User user) {
			Optional<User> userOptional = this.repository.findByUsername(user);

			return userOptional.isPresent();
	}
}
