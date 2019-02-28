package com.osmosis.duckr.bo;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Document
public class User {

	@Id
	public String id = StringUtils.EMPTY;

	@Indexed(unique = true)
	public String username = StringUtils.EMPTY;
	public String password = StringUtils.EMPTY;
	public String email = StringUtils.EMPTY;

	public LocalDateTime registerDate;
	public LocalDateTime lastLogin;
	public LocalDateTime lastLogoff;

	public String token = StringUtils.EMPTY;

	public List<Duck> duckList = new LinkedList<>();
	public List<Comment> comments = new LinkedList<>();
	public List<User> follows = new LinkedList<>();
}
