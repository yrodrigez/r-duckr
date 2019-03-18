package com.osmosis.duckr.bll;

import com.osmosis.duckr.bo.Duck;
import com.osmosis.duckr.dal.DuckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DuckManager {
	private final DuckRepository repository;

	@Autowired
	public DuckManager(final DuckRepository repository) {
		this.repository = repository;
	}

	public Optional<Duck> post(Duck duck) {
		return Optional.of(this.repository.save(duck));
	}
}
