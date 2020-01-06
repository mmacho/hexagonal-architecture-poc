package com.hexagonal.test;

import java.util.List;
import java.util.Optional;

import com.hexagonal.core.adapter.ExampleServiceAdapter;
import com.hexagonal.core.port.ExampleServicePort;
import com.hexagonal.domain.Example;
import com.hexagonal.domain.port.ExamplePersistencePort;

public class Main {

	private static final String EXAMPLE_NAME = "test";
	private static final int EXAMPLE_ID = 1;

	public static void main(String[] args) {
		final ExampleServicePort exampleServicePort = new ExampleServiceAdapter(createMockExamplePersistencePort());

		Optional<Example> op = exampleServicePort.getExampleById(EXAMPLE_ID);
		assert op.isPresent() == true;

		Example example = op.get();
		assert example.getId().equals(EXAMPLE_ID);
		assert example.getName().equals(EXAMPLE_NAME);

	}

	private static ExamplePersistencePort createMockExamplePersistencePort() {

		return new ExamplePersistencePort() {

			@Override
			public void addExample(Example example) {

			}

			@Override
			public void removeExample(Example example) {

			}

			@Override
			public void updateExample(Example example) {

			}

			@Override
			public List<Example> getAllExamples() {
				return null;
			}

			@Override
			public Optional<Example> getExampleById(Integer id) {
				return Optional.of(Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build());
			}

		};
	}

}
