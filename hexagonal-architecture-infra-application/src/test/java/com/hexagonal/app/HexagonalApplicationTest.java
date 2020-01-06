package com.hexagonal.app;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mule.api.MuleException;
import org.mule.tck.junit4.FunctionalTestCase;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import config.PropertiesConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PropertiesConfig.class })
public class HexagonalApplicationTest extends FunctionalTestCase {

	protected String getConfigResources() {
		return "";
	}

	@Test
	public void testFoo() throws MuleException {
		assertNotNull(1);
	}
}
