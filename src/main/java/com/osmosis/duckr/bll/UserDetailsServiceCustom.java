package com.osmosis.duckr.bll;

import com.osmosis.duckr.bo.User;
import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserDetailsServiceCustom implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public UserDetailsServiceCustom(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		final Optional<User> user = this.userRepository.findByUsername(username);

		if (user.isPresent())
			return new org.springframework.security.core.userdetails.User(user.get().username, user.get().password, user.get().authorities);
		throw new UsernameNotFoundException(username + " was not found!");
	}
}
