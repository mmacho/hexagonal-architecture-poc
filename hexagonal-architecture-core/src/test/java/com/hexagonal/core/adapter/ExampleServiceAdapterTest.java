package com.hexagonal.core.adapter;

import static java.util.Optional.empty;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hexagonal.domain.Example;
import com.hexagonal.domain.port.ExamplePersistencePort;

@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceAdapterTest {

	private static final Integer EXAMPLE_ID = 1;
	private static final String EXAMPLE_NAME = "name";

	@Mock
	private ExamplePersistencePort repo;

	@InjectMocks
	private ExampleServiceAdapter underTest;

	@Test
	public void findExample() {
		final Example demo = new Example(EXAMPLE_ID, EXAMPLE_NAME);
		when(repo.getExampleById(EXAMPLE_ID)).thenReturn(Optional.of(demo));

		Optional<Example> real = underTest.getExampleById(EXAMPLE_ID);

		assertEquals(Optional.of(demo), real);
	}

	@Test
	public void doesntExample() {
		when(repo.getExampleById(EXAMPLE_ID)).thenReturn(empty());

		Optional<Example> real = underTest.getExampleById(EXAMPLE_ID);

		assertThat(real, is(empty()));
	}

	@Test
	public void givenExampleId_whenGetExampleById_thenGetExampleByIdPortCalled() {
		final Example mockExample = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		when(repo.getExampleById(EXAMPLE_ID)).thenReturn(Optional.of(mockExample));

		underTest.getExampleById(EXAMPLE_ID).ifPresent(example -> {
			assertThat(example).isSameAs(mockExample);
			verify(repo, only()).getExampleById(EXAMPLE_ID);
		});

	}

}
