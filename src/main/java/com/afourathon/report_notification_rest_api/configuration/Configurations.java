package com.afourathon.report_notification_rest_api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class Configurations implements WebMvcConfigurer {

	public static final String INVALID_PROJECT_ID = "Invalid Project ID. Project doesn't exists with ID: %d.";

	public static final String INVALID_WEEKLY_STATUS_ID = "Invalid Weekly-Status ID. Weekly-Status doesn't exists with ID: %d";

	public static final String EMAIL_SUBJECT = "Weekly-Status Report of the Project: %s as of: %s";

	public static final String EMAIL_NOTIFICATION_ON_SUCCESS = "Unable to send an email report for the Weekly-Status ID: %d from the Project ID: %d.";

	public static final String EMAIL_NOTIFICATION_ON_FAILURE = "An email report for the Weekly-Status ID: %d from the Project ID: %d has been sent successfully.";

	@Value("${cors.origin.url}")
	private String CORS_ORIGIN_URL;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(CORS_ORIGIN_URL);
	}

	// Adding a bean definition to enable HTTP request logging
	@Bean
	public CommonsRequestLoggingFilter logFilter() {
		CommonsRequestLoggingFilter filter
		= new CommonsRequestLoggingFilter();
		filter.setIncludeQueryString(true);
		filter.setIncludePayload(true);
		filter.setMaxPayloadLength(10000);
		filter.setIncludeHeaders(false);
		filter.setAfterMessagePrefix("REQUEST DATA: ");
		return filter;
	}

}
