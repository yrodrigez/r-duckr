package com.osmosis.duckr.bo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("comments")
public class Comment {

	@Id
	public String id;
	public String duckId;

	public String userId;

	public String data = StringUtils.EMPTY;

}
