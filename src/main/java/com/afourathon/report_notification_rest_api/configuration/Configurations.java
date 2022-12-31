package com.afourathon.report_notification_rest_api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Configurations implements WebMvcConfigurer {
	
	public static final String INVALID_PROJECT_ID = "Invalid Project ID. Project doesn't exists with ID: %d.";
		
	public static final String INVALID_WEEKLY_STATUS_ID = "Invalid Weekly-Status ID. Weekly-Status doesn't exists with ID: %d";
	
	public static final String EMAIL_SUBJECT = "Weekly-Status Report of the Project: %s as of: %s";
	
	@Value("${cors.origin.url}")
	private String CORS_ORIGIN_URL;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println(CORS_ORIGIN_URL);
		registry.addMapping("/**").allowedOrigins(CORS_ORIGIN_URL);
	}
	
}
