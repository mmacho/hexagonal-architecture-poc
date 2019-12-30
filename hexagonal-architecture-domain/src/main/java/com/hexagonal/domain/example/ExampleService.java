package com.hexagonal.domain.example;

import java.util.Optional;

public interface ExampleService {

	Optional<Example> findById(final Integer identifier);
}
