package com.hexagonal.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySources({ @PropertySource("classpath:config/application.properties"),
		@PropertySource("classpath:config/persistence.properties"),
		@PropertySource("classpath:config/endpoints.properties"), @PropertySource("classpath:config/wsdl.properties"),
		@PropertySource("classpath:config/logging.properties") })
@Import({ DatabaseConfiguration.class, DataSourceInitializerConfiguration.class, RepositoryConfiguration.class,
		DomainConfiguration.class })
public class HexagonalConfiguration {

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
}
