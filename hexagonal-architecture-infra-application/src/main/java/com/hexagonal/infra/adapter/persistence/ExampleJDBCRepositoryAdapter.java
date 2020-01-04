package com.hexagonal.infra.adapter.persistence;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.NotImplementedException;

import com.hexagonal.domain.data.Example;
import com.hexagonal.domain.data.port.ExamplePersistencePort;
import com.hexagonal.persistence.jdbc.repository.ExampleModelRepository;

/**
 * Secundary Adapter, contains implementation for secondary ports defined in
 * pets-domain module. Implementations of these ports are called secondary
 * adapters – they directly communicate with specific datasources (databases,
 * filesystems, etc), they can also call other applications (REST, SOAP, JMS,
 * etc). In simple words, this module should contain implementation details
 * related with providing data (or with communicating with the world) for the
 * the domain
 * 
 * @author Conchi
 *
 */
public class ExampleJDBCRepositoryAdapter implements ExamplePersistencePort {

	private final ExampleModelRepository exampleModelRepository;

	public ExampleJDBCRepositoryAdapter(final ExampleModelRepository exampleModelRepository) {
		super();
		this.exampleModelRepository = exampleModelRepository;
	}

	@Override
	public void addExample(Example example) {
		throw new NotImplementedException("NotImplementedException");

	}

	@Override
	public void removeExample(Example example) {
		throw new NotImplementedException("NotImplementedException");

	}

	@Override
	public void updateExample(Example example) {
		throw new NotImplementedException("NotImplementedException");

	}

	@Override
	public List<Example> getAllExamples() {
		throw new NotImplementedException("NotImplementedException");
	}

	@Override
	public Optional<Example> getExampleById(Integer id) {
		return exampleModelRepository.findById(id).map(this::adapt);

	}

	/**
	 * 
	 * @param exampleModel
	 * @return
	 */
	private Example adapt(com.hexagonal.persistence.jdbc.model.ExampleModel exampleModel) {
		return new Example(exampleModel.getId(), exampleModel.getName());
	}

}
