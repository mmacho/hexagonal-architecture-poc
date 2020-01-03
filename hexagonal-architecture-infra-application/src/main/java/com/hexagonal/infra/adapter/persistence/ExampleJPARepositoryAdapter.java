package com.hexagonal.infra.adapter.persistence;

import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

import com.hexagonal.domain.example.Example;
import com.hexagonal.domain.example.ExamplePortRepository;
import com.hexagonal.persistence.jpa.entity.JpaExampleEntity;
import com.hexagonal.persistence.jpa.repository.ExampleEntityRepository;

@Transactional(readOnly = true)
public class ExampleJPARepositoryAdapter implements ExamplePortRepository {

	private ExampleEntityRepository exampleEntityRepository;

	public ExampleJPARepositoryAdapter(ExampleEntityRepository exampleEntityRepository) {
		super();
		this.exampleEntityRepository = exampleEntityRepository;
	}

	@Override
	public Optional<Example> findById(Integer identifier) {
		return exampleEntityRepository.findById(identifier).map(this::adapt);
	}

	private Example adapt(JpaExampleEntity jpaExampleEntity) {
		return new Example(jpaExampleEntity.getId());
	}

}
