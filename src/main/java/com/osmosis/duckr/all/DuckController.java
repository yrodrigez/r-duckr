package com.osmosis.duckr.all;

import com.osmosis.duckr.bll.DuckManager;
import com.osmosis.duckr.bo.Duck;
import com.osmosis.duckr.bo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("ducks")
public class DuckController {

    final
    DuckManager manager;

    @Autowired
    public DuckController(DuckManager manager) {
        this.manager = manager;
    }

    @PostMapping
    @RolesAllowed({User.ROLE_USER, User.ROLE_PUBLISHER})
    public Optional<Duck> post(@RequestBody Duck duck, Principal principal) {
        duck.createdBy = principal.getName();
        return this.manager.post(duck);
    }

    @GetMapping
    @RolesAllowed(User.ROLE_USER)
    public Collection<Duck> getDucks(Principal principal) {
        principal.getName();
        return Collections.emptyList();
    }


}
