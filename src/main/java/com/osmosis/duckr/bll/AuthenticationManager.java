package com.osmosis.duckr.bll;

import com.osmosis.duckr.bo.User;
import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

@Component
public class AuthenticationManager implements AuthenticationProvider {
	private final UserRepository userRepository;


	@Resource(name="authenticationManager")
	private org.springframework.security.authentication.AuthenticationManager authManager;

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

	private User.UserCredentials createCredentials(final User authentication) {
		User.UserCredentials credentials = new User.UserCredentials();
		credentials.username = authentication.getName();
		credentials.password = authentication.getPassword();

		return credentials;
	}

	public Authentication authenticate(final User authentication, final HttpServletRequest request) throws AuthenticationException {
		final Optional<User> user = checkCredentials(createCredentials(authentication));

		if(user.isPresent()){
			final User u = user.get();
			final UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(u.username, u.password);
			final Authentication auth = authManager.authenticate(token);
			final SecurityContext sc = SecurityContextHolder.getContext();
			sc.setAuthentication(auth);
			final HttpSession session = request.getSession(true);
			session.setAttribute("SPRING_SECURITY_CONTEXT", sc);

			return token;
		}

		throw new BadCredentialsException("Authentication failed");
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
