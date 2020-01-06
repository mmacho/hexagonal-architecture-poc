package com.hexagonal.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.core.adapter.ExampleServiceAdapter;
import com.hexagonal.domain.port.ExamplePersistencePort;

@Configuration
public class DomainConfiguration {

	@Bean
	public ExampleServiceAdapter exampleJPAServiceAdapter(ExamplePersistencePort exampleJPAPersistencePort) {
		return new ExampleServiceAdapter(exampleJPAPersistencePort);
	}

	@Bean
	public ExampleServiceAdapter exampleJDBCServiceAdapter(ExamplePersistencePort exampleJDBCPersistencePort) {
		return new ExampleServiceAdapter(exampleJDBCPersistencePort);
	}
}
