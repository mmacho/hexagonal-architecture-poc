package com.hexagonal.infra.adapter.persistence.jpa.adapter;

import static com.hexagonal.infra.adapter.persistence.jpa.adapter.ExampleJPARepositoryAdapterIT.ExampleJPARepositoryAdapterITApi.CLASSPATH_PERSISTENCE_PROPERTIES;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hexagonal.domain.Example;
import com.hexagonal.domain.port.ExamplePersistencePort;
import com.hexagonal.infra.DatabaseConfiguration;
import com.hexagonal.infra.RepositoryConfiguration.DataJPAConfiguration;

import config.PropertiesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesConfig.class, DatabaseConfiguration.class, DataJPAConfiguration.class })
@TestPropertySource(CLASSPATH_PERSISTENCE_PROPERTIES)
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeScripts.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterScripts.sql") })
public class ExampleJPARepositoryAdapterIT {

	private static final Integer EXAMPLE_ID = 1;
	private static final String EXAMPLE_NAME = "entity_1";

	private static final Integer EXAMPLE_NOT_FOUND_ID = 10;

	@Autowired
	private ExamplePersistencePort underTest;

	@Test
	public void findsExample() {
		final Example expectedExample = new Example(EXAMPLE_ID, EXAMPLE_NAME);

		Optional<Example> example = underTest.getExampleById(EXAMPLE_ID);

		assertEquals(Optional.of(expectedExample), example);
	}

	@Test
	public void notFindsExample() {
		Optional<Example> shirt = underTest.getExampleById(EXAMPLE_NOT_FOUND_ID);

		assertThat(shirt, is(empty()));
	}

	interface ExampleJPARepositoryAdapterITApi {
		String CLASSPATH_PERSISTENCE_PROPERTIES = "classpath:config/persistence.properties";
	}

}
