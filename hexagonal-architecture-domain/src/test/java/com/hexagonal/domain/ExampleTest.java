package com.hexagonal.domain;

import static org.assertj.core.api.Assertions.assertThat;
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
	public void givenTwoExamples_whenSameIdAndName_thenEqual() {
		final Example example1 = new Example(EXAMPLE_ID, EXAMPLE_NAME);
		final Example example2 = new Example(EXAMPLE_ID, EXAMPLE_NAME);
		assertEquals(example1, example2);
	}

	@Test
	public void givenName_whenExamplesWithName_thenNameIsSetwith_builder() {
		final Example example = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		assertEquals(example.getId(), EXAMPLE_ID);
		assertEquals(example.getName(), EXAMPLE_NAME);
	}

	@Test
	public void givenTwoExamples_whenSameIdAndName_thenEqual_with_builder() {
		final Example example1 = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		final Example example2 = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		assertEquals(example1, example2);
		assertThat(example1).isEqualTo(example2);
	}

	@Test
	public void givenTwoExamples_whenDifferentId_thenNotEqual() {
		final Example example1 = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		final Example example2 = Example.builder().id(2).name(EXAMPLE_NAME).build();
		assertThat(example1).isNotEqualTo(example2);
	}

	@Test
	public void givenTwoExamples_whenDifferentName_thenNotEqual() {
		final Example example1 = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		final Example example2 = Example.builder().id(EXAMPLE_ID).name("xx").build();
		assertThat(example1).isNotEqualTo(example2);
	}

	@Test
	public void givenTwoExamples_whenSameIdAndName_thenSameHashCode() {
		final Example example1 = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		final Example example2 = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		assertThat(example1.hashCode()).isEqualTo(example2.hashCode());
	}

	@Test
	public void givenTwoExamples_whenSameIdAndName_thenNotSameHashCode() {
		final Example example1 = Example.builder().id(1).name(EXAMPLE_NAME).build();
		final Example example2 = Example.builder().id(2).name(EXAMPLE_NAME).build();
		assertThat(example1.hashCode()).isNotEqualTo(example2.hashCode());
	}
}
