package com.hexagonal.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;

/**
 * Domain data structures and imutable
 * 
 * @author Conchi
 *
 */
@Value
@AllArgsConstructor
@Builder
public class Example {

	private final Integer id;

	private final String name;

}
