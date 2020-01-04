package com.hexagonal.infra.adapter.persistence;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.NotImplementedException;

import com.hexagonal.domain.data.Example;
import com.hexagonal.domain.data.port.ExamplePersistencePort;
import com.hexagonal.persistence.jpa.entity.JpaExampleEntity;
import com.hexagonal.persistence.jpa.repository.ExampleEntityRepository;

/**
 * Secundary Adapter, contains implementation for secondary ports defined in
 * pets-domain module. Implementations of these ports are called secondary
 * adapters � they directly communicate with specific datasources (databases,
 * filesystems, etc), they can also call other applications (REST, SOAP, JMS,
 * etc). In simple words, this module should contain implementation details
 * related with providing data (or with communicating with the world) for the
 * the domain
 * 
 * @author Conchi
 *
 */
public class ExampleJPARepositoryAdapter implements ExamplePersistencePort {

	private ExampleEntityRepository exampleEntityRepository;

	public ExampleJPARepositoryAdapter(ExampleEntityRepository exampleEntityRepository) {
		super();
		this.exampleEntityRepository = exampleEntityRepository;
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
		return exampleEntityRepository.findById(id).map(this::adapt);

	}

	private Example adapt(JpaExampleEntity jpaExampleEntity) {
		return new Example(jpaExampleEntity.getId(), jpaExampleEntity.getName());
	}
}
