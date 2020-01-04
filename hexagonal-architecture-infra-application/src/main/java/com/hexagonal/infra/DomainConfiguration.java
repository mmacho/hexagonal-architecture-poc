package com.hexagonal.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.core.adapter.ExampleServiceAdapter;
import com.hexagonal.domain.data.port.ExamplePersistencePort;

@Configuration
public class DomainConfiguration {

	@Bean
	public ExampleServiceAdapter exampleServiceAdapter(final ExamplePersistencePort examplePersistencePort) {
		return new ExampleServiceAdapter(examplePersistencePort);
	}

	@Bean
	public ExampleServiceAdapter exampleServiceAdapter2(final ExamplePersistencePort examplePersistencePort2) {
		return new ExampleServiceAdapter(examplePersistencePort2);
	}
}
