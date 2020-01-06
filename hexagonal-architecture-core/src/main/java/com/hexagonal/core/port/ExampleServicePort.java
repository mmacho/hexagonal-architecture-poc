package com.hexagonal.core.port;

import java.util.Optional;

import com.hexagonal.domain.Example;

/**
 * Primary Port, used for calling business logic (like entry point for this
 * module)
 * 
 * @author Conchi
 *
 */
public interface ExampleServicePort {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Example> getExampleById(Integer id);

}
