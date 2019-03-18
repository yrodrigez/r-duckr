package com.osmosis.duckr.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Document("ducks")
public class Duck  implements BO {

	@Id
	public String id;

	public String content;

	public LocalDateTime created = LocalDateTime.now();

	public String createdBy;
	public Long stars = 0L;

	@DBRef
	public List<Comment> comments = Collections.emptyList();

}
