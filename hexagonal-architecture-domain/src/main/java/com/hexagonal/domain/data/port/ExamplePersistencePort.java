package com.hexagonal.domain.data.port;

import java.util.List;
import java.util.Optional;

import com.hexagonal.domain.data.Example;

/**
 * Secundary Port, which domain should depend on, must also be defined here, but
 * without the implementation. These ports are used to communicate with the
 * world. Interface of all the ports should depend only on domain model
 * 
 * @author Conchi
 *
 */
public interface ExamplePersistencePort {

	void addExample(Example example);

	void removeExample(Example example);

	void updateExample(Example example);

	List<Example> getAllExamples();

	Optional<Example> getExampleById(Integer id);
}
