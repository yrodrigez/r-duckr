package com.osmosis.duckr.all;

import com.osmosis.duckr.bll.UserManager;
import com.osmosis.duckr.bo.BO;
import com.osmosis.duckr.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

	final private UserManager manager;

	@Autowired
	public UserController(final UserManager manager) {
		this.manager = manager;
	}


	@PostMapping
	public Authentication signUp(@RequestBody final User user){
		return this.manager.signUp(user);
	}

	@PostMapping("/register")
	public BO register(@RequestBody final User user) {

		return this.manager.registerUser(user);
	}

}
