package com.hexagonal.domain.exception;

public class ExampleException extends RuntimeException {

	private static final long serialVersionUID = -2728894312496536700L;

	public ExampleException(Integer id) {
		super(String.format("Received request for id %s", id));
	}
}
