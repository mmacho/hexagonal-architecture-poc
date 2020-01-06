package com.hexagonal.core.adapter;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hexagonal.core.port.ExampleServicePort;
import com.hexagonal.domain.Example;
import com.hexagonal.domain.port.ExamplePersistencePort;

import lombok.RequiredArgsConstructor;

/**
 * 
 * 
 * @author Conchi
 *
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExampleServiceAdapter implements ExampleServicePort {

	private final ExamplePersistencePort examplePersistencePort;

	@Override
	public Optional<Example> getExampleById(Integer id) {
		return examplePersistencePort.getExampleById(id);
	}

}
