package com.osmosis.duckr.configuration;

import com.mongodb.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

@Configuration
public class MongoConfig extends AbstractMongoConfiguration {


	@NotNull
	@Override
	public MongoClient mongoClient() {
		return new MongoClient();
	}

	@NotNull
	@Override
	protected String getDatabaseName() {
		return "duckr";
	}
}