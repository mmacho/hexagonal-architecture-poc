package com.hexagonal.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources({
	@PropertySource("classpath:config/application.properties"),
	@PropertySource("classpath:config/persistence.properties"),
	@PropertySource("classpath:config/endpoints.properties"),
	@PropertySource("classpath:config/wsdl.properties"),
	@PropertySource("classpath:config/logging.properties")
})
@Import({ DataSourceInitializerConfiguration.class, DatabaseConfiguration.class, DomainConfiguration.class, RepositoryConfiguration.class })
public class HexagonalConfiguration {

}
