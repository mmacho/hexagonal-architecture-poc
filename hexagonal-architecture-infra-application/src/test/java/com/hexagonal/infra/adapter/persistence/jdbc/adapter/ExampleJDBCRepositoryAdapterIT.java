package com.hexagonal.infra.adapter.persistence.jdbc.adapter;

import static com.hexagonal.infra.adapter.persistence.jdbc.adapter.ExampleJDBCRepositoryAdapterIT.ExampleJDBCRepositoryAdapterITApi.CLASSPATH_PERSISTENCE_PROPERTIES;
import static org.assertj.core.api.Assertions.assertThat;

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
import com.hexagonal.infra.RepositoryConfiguration.DataJdbcConfiguartion;

import config.PropertiesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesConfig.class, DatabaseConfiguration.class, DataJdbcConfiguartion.class })
@TestPropertySource(CLASSPATH_PERSISTENCE_PROPERTIES)
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeScripts.sql"),
		@Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterScripts.sql") })
public class ExampleJDBCRepositoryAdapterIT {

	interface ExampleJDBCRepositoryAdapterITApi {
		String CLASSPATH_PERSISTENCE_PROPERTIES = "classpath:config/persistence.properties";
	}

	private static final Integer EXAMPLE_ID = 1;

	@Autowired
	private ExamplePersistencePort underTest;

	@Test
	public void findById_exampleJDBCPersistencePort_Domain() {
		Optional<Example> op = underTest.getExampleById(EXAMPLE_ID);
		op.ifPresent(example -> {
			assertThat(example.getId()).isEqualTo(EXAMPLE_ID);
		});
	}

}
