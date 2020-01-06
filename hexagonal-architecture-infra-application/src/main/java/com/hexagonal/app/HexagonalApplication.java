package com.hexagonal.app;

import org.springframework.context.annotation.Import;

import com.hexagonal.infra.HexagonalConfiguration;

@Import(HexagonalConfiguration.class)
public class HexagonalApplication {

}
