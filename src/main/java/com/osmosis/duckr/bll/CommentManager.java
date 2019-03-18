package com.osmosis.duckr.bll;

import com.osmosis.duckr.dal.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentManager {
	private final CommentRepository repository;

	@Autowired
	public CommentManager(final CommentRepository repository) {
		this.repository = repository;
	}


}
