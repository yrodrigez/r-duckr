package com.osmosis.duckr.bll;

import com.osmosis.duckr.bo.User;
import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationManager implements AuthenticationProvider {
	private final UserRepository userRepository;

	@Autowired
	public AuthenticationManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private Optional<User> checkCredentials(final User.UserCredentials credentials) {
		final Optional<User> user = this.userRepository.findByUsername(credentials.username);
		if (user.isPresent() && user.get().username.equals(credentials.username) && user.get().password.equals(credentials.password)) {
			return user;
		}

		return Optional.empty();
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		User.UserCredentials credentials = new User.UserCredentials();
		credentials.username = authentication.getName();
		credentials.password = authentication.getCredentials().toString();

		final Optional<User> user = checkCredentials(credentials);
		if (user.isPresent()) {
			return new UsernamePasswordAuthenticationToken(user.get().username, user.get().password, user.get().authorities);
		} else {
			throw new BadCredentialsException("Authentication failed");
		}
	}

	@Override
	public boolean supports(final Class<?> aClass) {
		return aClass.equals(UsernamePasswordAuthenticationToken.class);
	}
}
