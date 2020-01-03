package com.hexagonal.domain.example;

import java.util.Optional;

public interface ExamplePortRepository {

	Optional<Example> findById(Integer identifier);
}
