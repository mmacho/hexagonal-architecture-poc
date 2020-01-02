package infra.adapter.persistence.test;

import static infra.adapter.persistence.test.ExampleJPARepositoryAdapterIT.ExampleJPARepositoryAdapterITApi.CLASSPATH_PERSISTENCE_PROPERTIES;
import static java.util.Optional.empty;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hexagonal.domain.example.Example;
import com.hexagonal.domain.example.ExamplePortRepository;
import com.hexagonal.infra.DataSourceInitializerConfiguration;
import com.hexagonal.infra.DatabaseConfiguration;
import com.hexagonal.infra.DomainConfiguration;
import com.hexagonal.infra.RepositoryConfiguration;

import config.PropertiesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesConfig.class, DatabaseConfiguration.class,
		DataSourceInitializerConfiguration.class, DomainConfiguration.class, RepositoryConfiguration.class })
@TestPropertySource(CLASSPATH_PERSISTENCE_PROPERTIES)
public class ExampleJPARepositoryAdapterIT extends AbstractTransactionalJUnit4SpringContextTests {

	private static final int EXAMPLE_FOUND_ID = 1;
	private static final int EXAMPLE_NOT_FOUND_ID = 10;

	@Autowired
	private ExamplePortRepository underTest;

	@Test
	public void findsExample() {
		Example expectedExample = new Example(EXAMPLE_FOUND_ID);

		Optional<Example> example = underTest.findById(EXAMPLE_FOUND_ID);

		assertEquals(Optional.of(expectedExample), example);
	}

	@Test
	public void notFindsExample() {
		Optional<Example> shirt = underTest.findById(EXAMPLE_NOT_FOUND_ID);

		assertThat(shirt, is(empty()));
	}

	interface ExampleJPARepositoryAdapterITApi {
		String CLASSPATH_PERSISTENCE_PROPERTIES = "classpath:persistence.properties";
	}

}
