package com.osmosis.duckr.dal;

import com.osmosis.duckr.bo.Duck;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DuckRepository extends MongoRepository<Duck, String> {
}
