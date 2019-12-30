package com.hexagonal.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceInitializerConfiguration {

	@Value("classpath:db/initDB.sql")
	private Resource initLocation;

	@Value("classpath:db/populateDB.sql")
	private Resource populateDB;

	@Bean
	public DataSourceInitializer dataSourceInitializer(final HikariDataSource dataSource) {
		final DataSourceInitializer initializer = new DataSourceInitializer();
		initializer.setDataSource(dataSource);
		initializer.setDatabasePopulator(databasePopulator());
		return initializer;
	}

	private DatabasePopulator databasePopulator() {
		final ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
		populator.addScript(initLocation);
		populator.addScript(populateDB);
		return populator;
	}
}
