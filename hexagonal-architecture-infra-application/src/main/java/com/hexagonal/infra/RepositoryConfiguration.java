package com.hexagonal.infra;

import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.hexagonal.domain.example.ExamplePortRepository;
import com.hexagonal.infra.adapter.persistence.ExampleJPARepositoryAdapter;
import com.hexagonal.persistence.jpa.entity.JpaExampleEntity;
import com.hexagonal.persistence.jpa.repository.ExampleEntityRepository;

@Configuration
public class RepositoryConfiguration {

	@Configuration
	@EnableTransactionManagement
	@EnableJpaRepositories(basePackageClasses = { ExampleEntityRepository.class })
	@EntityScan(basePackageClasses = { JpaExampleEntity.class })
	public static class DataJPAConfiguration {

		@Bean
		public ExamplePortRepository examplePortRepository(final ExampleEntityRepository exampleEntityRepository) {
			return new ExampleJPARepositoryAdapter(exampleEntityRepository);
		}
	}

}
