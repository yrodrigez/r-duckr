package com.osmosis.duckr.dal;

import com.osmosis.duckr.bo.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
	@Query("{ username: ?0 }")
	Optional<User> findByUsername(final String username);
	@Query("{ _id: ?0 }")
	Optional<User> findByEmail(final String email);
}
