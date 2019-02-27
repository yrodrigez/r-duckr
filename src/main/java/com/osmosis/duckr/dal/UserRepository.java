package com.osmosis.duckr.dal;

import com.osmosis.duckr.bo.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
