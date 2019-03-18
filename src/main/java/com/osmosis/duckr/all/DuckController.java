package com.osmosis.duckr.all;

import com.osmosis.duckr.bo.Duck;
import com.osmosis.duckr.bo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("ducks")
public class DuckController {


	@GetMapping
	@RolesAllowed(User.ROLE_USER)
	public Collection<Duck> getDucks(Principal principal) {
		principal.getName();
		return Collections.emptyList();
	}


}
