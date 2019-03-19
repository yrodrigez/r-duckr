package com.osmosis.duckr.configuration;

import com.osmosis.duckr.bll.AuthenticationManager;
import com.osmosis.duckr.dal.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeans {
	private final UserRepository manager;

	@Autowired
	public AppBeans(final UserRepository manager) {
		this.manager = manager;
	}


	@Bean
	public com.osmosis.duckr.bll.AuthenticationManager authenticationManagerBean() {
		return new AuthenticationManager(manager);
	}


}
