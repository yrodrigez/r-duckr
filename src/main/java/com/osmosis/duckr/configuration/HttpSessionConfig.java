package com.osmosis.duckr.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.geo.GeoModule;
import org.springframework.session.data.mongo.AbstractMongoSessionConverter;
import org.springframework.session.data.mongo.JacksonMongoSessionConverter;
import org.springframework.session.data.mongo.config.annotation.web.http.EnableMongoHttpSession;

import java.util.Collections;

@Configuration
@EnableMongoHttpSession
public class HttpSessionConfig {

	@Bean
	AbstractMongoSessionConverter mongoSessionConverter() {
		return new JacksonMongoSessionConverter(Collections.singletonList(new GeoModule()));
	}
}
