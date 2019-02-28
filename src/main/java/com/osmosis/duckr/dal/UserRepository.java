package com.osmosis.duckr.dal;

import com.osmosis.duckr.bo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(final User user);
}
