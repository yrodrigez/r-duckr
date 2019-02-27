package com.osmosis.duckr.dal;

import com.osmosis.duckr.bo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommentRepository extends MongoRepository<Comment, String> {
}
