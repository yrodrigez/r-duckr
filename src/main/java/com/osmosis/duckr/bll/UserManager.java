package com.osmosis.duckr.bll;

import com.osmosis.duckr.bo.BO;
import com.osmosis.duckr.bo.Error;
import com.osmosis.duckr.bo.User;
import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class UserManager {

	private final UserRepository repository;
	private final AuthenticationManager authenticationManager;
	private final ApplicationContext context;

	@Autowired
	public UserManager(final UserRepository repository, final AuthenticationManager authenticationManager, final ApplicationContext context) {
		this.repository = repository;
		this.authenticationManager = authenticationManager;
		this.context = context;
	}

	public Authentication login(final User user, final HttpServletRequest request) {
		return this.authenticationManager.authenticate(user, request);
	}

	public BO registerUser(final User user) {
		if (isUserRegistered(user)) {
			Error error = new Error();
			error.code = "404";
			error.message = "The user with email and username already exists";

			return error;
		}

		List<GrantedAuthority> authorityList = new LinkedList<>(Arrays.asList(
			new SimpleGrantedAuthority(User.ROLE_PUBLISHER),
			new SimpleGrantedAuthority(User.ROLE_USER),
			new SimpleGrantedAuthority(User.ROLE_COMMENTER)
		));


		final User newUser = new User();
		newUser.username = user.username;
		PasswordEncoder encoder = this.context.getBean(PasswordEncoder.class);
		newUser.password = encoder.encode(user.password);
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
