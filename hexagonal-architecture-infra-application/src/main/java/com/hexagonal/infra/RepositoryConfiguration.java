package com.hexagonal.infra;

import javax.sql.DataSource;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cdyne.ws.weatherws.WeatherSoap;
import com.hexagonal.domain.data.port.ExamplePersistencePort;
import com.hexagonal.external.system.ws.client.WeatherSoapClient;
import com.hexagonal.infra.adapter.persistence.ExampleJDBCRepositoryAdapter;
import com.hexagonal.infra.adapter.persistence.ExampleJPARepositoryAdapter;
import com.hexagonal.persistence.jdbc.repository.ExampleModelRepository;
import com.hexagonal.persistence.jdbc.repository.ExampleModelRepositoryImpl;
import com.hexagonal.persistence.jpa.entity.JpaExampleEntity;
import com.hexagonal.persistence.jpa.repository.ExampleEntityRepository;

@Configuration
public class RepositoryConfiguration {

	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackageClasses = {
			ExampleEntityRepository.class }, entityManagerFactoryRef = DatabaseConfiguration.ENTITY_MANAGER_FACTORY, transactionManagerRef = DatabaseConfiguration.TRANSACTION_MANAGER)
	@EntityScan(basePackageClasses = { JpaExampleEntity.class })
	public static class DataJPAConfiguration {

		@Bean
		public ExamplePersistencePort examplePersistencePort(final ExampleEntityRepository exampleEntityRepository) {
			return new ExampleJPARepositoryAdapter(exampleEntityRepository);
		}
	}

	@Configuration
	public static class DataJdbcConfiguartion {

		@Bean
		DataSourceTransactionManager dataSourceTransactionManager(final DataSource dataSource) {
			return new DataSourceTransactionManager(dataSource);
		}

		@Bean
		public JdbcTemplate jdbcTemplate(final DataSource dataSource) {
			return new JdbcTemplate(dataSource);
		}

		@Bean
		public ExampleModelRepository exampleModelRepository(final JdbcTemplate jdbcTemplate) {
			return new ExampleModelRepositoryImpl(jdbcTemplate);
		}

		@Bean
		public ExamplePersistencePort examplePersistencePort2(final ExampleModelRepository exampleModelRepository) {
			return new ExampleJDBCRepositoryAdapter(exampleModelRepository);
		}

	}

	@Configuration
	public static class SOAPConfiguration {

		@Value("${client.weatherSoap.address}")
		private String address;

		@Bean
		public WeatherSoap weatherSoap() {
			JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
			jaxWsProxyFactoryBean.setServiceClass(WeatherSoap.class);
			jaxWsProxyFactoryBean.setAddress(address);
			return (WeatherSoap) jaxWsProxyFactoryBean.create();
		}

		@Bean
		public WeatherSoapClient weatherSoapClient(final WeatherSoap weatherSoap) {
			return new WeatherSoapClient(weatherSoap);
		}
	}

}
