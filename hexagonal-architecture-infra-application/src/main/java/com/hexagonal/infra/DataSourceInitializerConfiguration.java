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

	private static final String $_JDBC_PASSWORD = "${jdbc.password}";
	private static final String $_JDBC_USERNAME = "${jdbc.username}";
	private static final String $_JDBC_URL = "${jdbc.url}";

	
	private static final String DB_POPULATE_DB_SQL = "classpath:db/populateDB.sql";
	private static final String DB_INIT_DB_SQL = "classpath:db/initDB.sql";

	@Value(DB_INIT_DB_SQL)
	private Resource initLocation;

	@Value(DB_POPULATE_DB_SQL)
	private Resource populateDB;


	
	@Bean(destroyMethod = "close")
	public HikariDataSource dataSource(@Value($_JDBC_URL) final String jdbcUrl, @Value($_JDBC_USERNAME) final String username,
			@Value($_JDBC_PASSWORD) final String password) {
		final HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setMaximumPoolSize(20);
		hikariDataSource.setMinimumIdle(5);
		hikariDataSource.setDriverClassName("org.h2.Driver");
		hikariDataSource.setJdbcUrl(jdbcUrl);
		hikariDataSource.setUsername(username);
		hikariDataSource.setPassword(password);
		return hikariDataSource;
	}
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
