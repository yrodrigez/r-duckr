package com.osmosis.duckr.bo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Document
public class Duck  implements BO {

	@Id
	public String id = StringUtils.EMPTY;
	public String content = StringUtils.EMPTY;

	public LocalDateTime created = LocalDateTime.now();

	public String createdById = StringUtils.EMPTY;
	public Long stars = 0L;

	public List<Comment> comments = new LinkedList<>();

}
