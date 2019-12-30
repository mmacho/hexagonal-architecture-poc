package com.hexagonal.domain.example;

import java.util.Optional;

public class ExampleServiceAdapter implements ExampleService {

	private final ExamplePortRepository examplePortRepository;
	
	
	public ExampleServiceAdapter(final ExamplePortRepository examplePortRepository) {
		super();
		this.examplePortRepository = examplePortRepository;
	}


	@Override
	public Optional<Example> findById(final Integer identifier) {
		return examplePortRepository.findById(identifier);
	}

}
