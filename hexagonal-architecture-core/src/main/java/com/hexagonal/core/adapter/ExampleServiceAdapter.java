package com.hexagonal.core.adapter;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.hexagonal.core.port.ExampleServicePort;
import com.hexagonal.domain.data.Example;
import com.hexagonal.domain.data.port.ExamplePersistencePort;

/**
 * 
 * 
 * @author Conchi
 *
 */
@Transactional(readOnly = true)
public class ExampleServiceAdapter implements ExampleServicePort {

	private final ExamplePersistencePort examplePersistencePort;

	public ExampleServiceAdapter(ExamplePersistencePort examplePersistencePort) {
		super();
		this.examplePersistencePort = examplePersistencePort;
	}

	@Override
	public Optional<Example> getExampleById(Integer id) {
		return examplePersistencePort.getExampleById(id);
	}

}
