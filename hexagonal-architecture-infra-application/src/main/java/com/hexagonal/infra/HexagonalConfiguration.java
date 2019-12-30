package com.hexagonal.infra;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ DatabaseConfiguration.class, DataSourceInitializerConfiguration.class, DomainConfiguration.class, RepositoryConfiguration.class })
public class HexagonalConfiguration {

}
