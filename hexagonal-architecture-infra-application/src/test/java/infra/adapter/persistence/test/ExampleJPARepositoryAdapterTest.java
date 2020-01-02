package infra.adapter.persistence.test;

import static java.util.Optional.empty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.hexagonal.domain.example.Example;
import com.hexagonal.infra.adapter.persistence.ExampleJPARepositoryAdapter;
import com.hexagonal.persistence.jpa.entity.JpaExampleEntity;
import com.hexagonal.persistence.jpa.repository.ExampleEntityRepository;

@RunWith(MockitoJUnitRunner.class)
public class ExampleJPARepositoryAdapterTest {

	private static final int ANY_EXAMPLE_ID = 1;

	@Mock
	private ExampleEntityRepository exampleEntityRepository;

	@InjectMocks
	private ExampleJPARepositoryAdapter underTest;

	@Test
	public void findsExample() {

		Example expectedExample = new Example(ANY_EXAMPLE_ID);

		JpaExampleEntity jpaExampleEntity = new JpaExampleEntity();
		jpaExampleEntity.setId(ANY_EXAMPLE_ID);

		when(exampleEntityRepository.findById(ANY_EXAMPLE_ID)).thenReturn(Optional.of(jpaExampleEntity));

		Optional<Example> example = underTest.findById(ANY_EXAMPLE_ID);

		assertEquals(Optional.of(expectedExample), example);
	}

	@Test
	public void notFindsExample() {
		when(exampleEntityRepository.findById(ANY_EXAMPLE_ID)).thenReturn(empty());

		Optional<Example> example = underTest.findById(ANY_EXAMPLE_ID);

		assertThat(example, is(empty()));
	}
}
