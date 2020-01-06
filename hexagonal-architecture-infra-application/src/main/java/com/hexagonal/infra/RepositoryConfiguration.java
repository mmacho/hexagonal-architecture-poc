package com.hexagonal.infra;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.cdyne.ws.weatherws.WeatherSoap;
import com.hexagonal.domain.port.ExamplePersistencePort;
import com.hexagonal.external.system.ws.adapter.WeatherSoapClientAdapter;
import com.hexagonal.persistence.jdbc.adapter.ExampleJDBCRepositoryAdapter;
import com.hexagonal.persistence.jdbc.repository.ExampleModelRepository;
import com.hexagonal.persistence.jdbc.repository.ExampleModelRepositoryImpl;
import com.hexagonal.persistence.jpa.adapter.ExampleJPARepositoryAdapter;
import com.hexagonal.persistence.jpa.entity.JpaExampleEntity;
import com.hexagonal.persistence.jpa.repository.ExampleEntityRepository;

@Configuration
public class RepositoryConfiguration {

	public static final String ENTITY_MANAGER_FACTORY = "entityManagerFactory";
	public static final String TRANSACTION_MANAGER = "transactionManager";

	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackageClasses = {
			ExampleEntityRepository.class }, entityManagerFactoryRef = RepositoryConfiguration.ENTITY_MANAGER_FACTORY, transactionManagerRef = RepositoryConfiguration.TRANSACTION_MANAGER)
	@EntityScan(basePackageClasses = { JpaExampleEntity.class })
	public static class DataJPAConfiguration {

		private static final String $_HIBERNATE_SHOW_SQL = "${hibernate.show_sql}";
		private static final String $_HIBERNATE_FORMAT_SQL = "${hibernate.format_sql}";

		@Bean(name = ENTITY_MANAGER_FACTORY)
		public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
				JpaVendorAdapter jpaVendorAdapter) {
			final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
			entityManagerFactoryBean.setDataSource(dataSource);

			entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

			entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
			entityManagerFactoryBean.setJpaProperties(additionalProperties());
			return entityManagerFactoryBean;
		}

		Properties additionalProperties() {
			Properties properties = new Properties();
			properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			properties.setProperty("hibernate.hbm2ddl.auto", "update");
			properties.setProperty("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
			properties.setProperty("hibernate.show_sql", $_HIBERNATE_SHOW_SQL);
			properties.setProperty("hibernate.format_sql", $_HIBERNATE_FORMAT_SQL);
			return properties;
		}

		@Bean
		public JpaVendorAdapter jpaVendorAdapter() {
			HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
			hibernateJpaVendorAdapter.setDatabase(Database.H2);
			return hibernateJpaVendorAdapter;
		}

		@Bean(name = TRANSACTION_MANAGER)
		@Primary
		public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
			final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
			jpaTransactionManager.setEntityManagerFactory(emf);
			return jpaTransactionManager;

		}

		@Bean
		public SharedEntityManagerBean entityManager(EntityManagerFactory emf) {
			SharedEntityManagerBean sharedEntityManagerBean = new SharedEntityManagerBean();
			sharedEntityManagerBean.setEntityManagerFactory(emf);
			return sharedEntityManagerBean;

		}

		@Bean
		public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
			return new PersistenceExceptionTranslationPostProcessor();
		}

		/** bean */
		@Bean
		public ExamplePersistencePort exampleJPAPersistencePort(ExampleEntityRepository exampleEntityRepository) {
			return new ExampleJPARepositoryAdapter(exampleEntityRepository);
		}
	}

	@Configuration
	public static class DataJdbcConfiguartion {

		@Bean
		public JdbcTemplate jdbcTemplate(DataSource dataSource) {
			return new JdbcTemplate(dataSource);
		}

		@Bean
		public ExampleModelRepository exampleModelRepository(JdbcTemplate jdbcTemplate) {
			return new ExampleModelRepositoryImpl(jdbcTemplate);
		}

		@Bean
		public ExamplePersistencePort exampleJDBCPersistencePort(ExampleModelRepository exampleModelRepository) {
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
		public WeatherSoapClientAdapter weatherSoapClientAdapter(WeatherSoap weatherSoap) {
			return new WeatherSoapClientAdapter(weatherSoap);
		}
	}

}
