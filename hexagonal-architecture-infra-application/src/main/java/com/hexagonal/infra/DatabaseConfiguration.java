package com.hexagonal.infra;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.SharedEntityManagerBean;
import org.springframework.orm.jpa.vendor.AbstractJpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {

	private static final String $_HIBERNATE_SHOW_SQL = "${hibernate.show_sql}";
	private static final String $_HIBERNATE_FORMAT_SQL = "${hibernate.format_sql}";
	
	private static final String $_JPA_PACKAGES_TO_SCAN = "${jpa.packagesToScan}";
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(final HikariDataSource dataSource,
			@Value($_JPA_PACKAGES_TO_SCAN) String packagesToScan, JpaVendorAdapter jpaVendorAdapter) {
		final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean  = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource);
		entityManagerFactoryBean.setPackagesToScan((new String[] {packagesToScan }));
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);

		entityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		entityManagerFactoryBean.setJpaProperties(additionalProperties());
		return entityManagerFactoryBean ;

	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		final AbstractJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.H2);
		return jpaVendorAdapter;
	}
	@Bean
	public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
		final JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;

	}

	@Bean
	public SharedEntityManagerBean entityManager(final EntityManagerFactory emf) {
		SharedEntityManagerBean sharedEntityManagerBean = new SharedEntityManagerBean();
		sharedEntityManagerBean.setEntityManagerFactory(emf);
		return sharedEntityManagerBean;

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
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}
