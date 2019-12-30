package com.hexagonal.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.hexagonal.domain.example.ExamplePortRepository;
import com.hexagonal.domain.example.ExampleServiceAdapter;

@Configuration
public class DomainConfiguration {

	@Bean
	public ExampleServiceAdapter exampleServiceAdapter(final ExamplePortRepository examplePortRepository) {
		return new ExampleServiceAdapter(examplePortRepository);
	}
}
