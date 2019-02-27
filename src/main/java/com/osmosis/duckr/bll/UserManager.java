package com.osmosis.duckr.bll;

import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserManager {

	private final UserRepository repository;

	@Autowired
	public UserManager(final UserRepository repository) {
		this.repository = repository;
	}
}
