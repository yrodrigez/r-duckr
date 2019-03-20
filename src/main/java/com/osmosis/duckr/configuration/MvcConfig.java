package com.osmosis.duckr.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry){
		registry.addInterceptor(new UserInterceptor());
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {

		registry.addResourceHandler("/static/**")
			.addResourceLocations("/WEB-INF/view/react/build/static/");
		registry.addResourceHandler("/*.js")
			.addResourceLocations("/WEB-INF/view/react/build/");
		registry.addResourceHandler("/*.json")
			.addResourceLocations("/WEB-INF/view/react/build/");
		registry.addResourceHandler("/*.ico")
			.addResourceLocations("/WEB-INF/view/react/build/");
		registry.addResourceHandler("/index.html")
			.addResourceLocations("/WEB-INF/view/react/build/index.html");
	}
}