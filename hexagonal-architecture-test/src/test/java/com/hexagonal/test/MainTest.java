package com.hexagonal.test;

import static com.hexagonal.test.Main.main;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.BeforeClass;
import org.junit.Test;

public class MainTest {

	@BeforeClass
	public static void beforeAll() {
		ClassLoader.getSystemClassLoader().setDefaultAssertionStatus(true);
	}

	@Test
	public void testMain() {
		assertAll(() -> main(null));
	}

}
