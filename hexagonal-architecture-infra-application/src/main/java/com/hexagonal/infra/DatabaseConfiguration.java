package com.hexagonal.infra;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

	private static final String $_JDBC_PASSWORD = "${jdbc.password}";
	private static final String $_JDBC_USERNAME = "${jdbc.username}";
	private static final String $_JDBC_URL = "${jdbc.url}";

	@Bean(destroyMethod = "close")
	@Primary
	public DataSource dataSource(@Value($_JDBC_URL) String jdbcUrl, @Value($_JDBC_USERNAME) String username,
			@Value($_JDBC_PASSWORD) String password) {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("org.h2.Driver");
		hikariConfig.setJdbcUrl(jdbcUrl);
		hikariConfig.setUsername(username);
		hikariConfig.setPassword(password);
		hikariConfig.setMaximumPoolSize(5);
		hikariConfig.setConnectionTestQuery("SELECT 1");
		hikariConfig.setPoolName("springHikariCP");
		HikariDataSource dataSource = new HikariDataSource(hikariConfig);
		return dataSource;
	}

}
