package com.hexagonal.domain.data;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExampleTest {

	private static final Integer EXAMPLE_ID = 1;
	private static final String EXAMPLE_NAME = "name";

	@Test
	public void givenName_whenExamplesWithName_thenNameIsSet() {
		final Example example = new Example(EXAMPLE_ID, EXAMPLE_NAME);
		assertEquals(example.getId(), EXAMPLE_ID);
		assertEquals(example.getName(), EXAMPLE_NAME);
	}

	@Test
	public void givenTwoExamples_whenSameNameAndExample_thenEqual() {
		final Example example1 = new Example(EXAMPLE_ID, EXAMPLE_NAME);
		final Example example2 = new Example(EXAMPLE_ID, EXAMPLE_NAME);
		assertEquals(example1, example2);
	}
}
