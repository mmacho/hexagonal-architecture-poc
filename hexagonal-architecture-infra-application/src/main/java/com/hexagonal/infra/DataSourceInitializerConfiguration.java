package com.hexagonal.infra;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Configuration
public class DataSourceInitializerConfiguration {

	public static final String DB_POPULATE_DB_SQL = "classpath:db/populateDB.sql";
	public static final String DB_INIT_DB_SQL = "classpath:db/initDB.sql";

	@Value(DB_INIT_DB_SQL)
	private Resource initLocation;

	@Value(DB_POPULATE_DB_SQL)
	private Resource populateDB;

	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
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
