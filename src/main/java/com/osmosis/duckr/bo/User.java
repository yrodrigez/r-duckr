package com.osmosis.duckr.bo;


import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.LinkedList;
import java.util.List;

@Document
public class User {

	@Id
	public String id = StringUtils.EMPTY;
	public String name = StringUtils.EMPTY;
	public String email = StringUtils.EMPTY;

	public List<Duck> duckList = new LinkedList<>();
	public List<Comment> comments = new LinkedList<>();
	public List<User> follows = new LinkedList<>();
}
