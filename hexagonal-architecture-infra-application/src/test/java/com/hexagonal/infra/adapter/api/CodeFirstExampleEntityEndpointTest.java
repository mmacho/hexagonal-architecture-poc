package com.hexagonal.infra.adapter.api;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

import java.util.Optional;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.hexagonal.core.port.ExampleServicePort;
import com.hexagonal.domain.Example;
import com.hexagonal.infra.adapter.api.model.XmlExampleEntity;

@Ignore
@RunWith(PowerMockRunner.class)
@PrepareForTest(Converters.class)
public class CodeFirstExampleEntityEndpointTest {

	private static final Integer EXAMPLE_ID = 1;
	private static final String EXAMPLE_NAME = "name";

	@Mock
	private ExampleServicePort jpaMock;

	@Mock
	private ExampleServicePort jdbcMock;

	@InjectMocks
	private CodeFirstExampleEntityEndpoint underTest;

	@Before
	public void setUp() {
		mockStatic(Converters.class);

	}

	@Test
	public void getEntity() {

		Example example = Example.builder().id(EXAMPLE_ID).name(EXAMPLE_NAME).build();
		when(jpaMock.getExampleById(EXAMPLE_ID)).thenReturn(Optional.of(example));

		XmlExampleEntity xmlExampleEntityMock = new XmlExampleEntity();
		xmlExampleEntityMock.setId(EXAMPLE_ID);
		xmlExampleEntityMock.setName(EXAMPLE_NAME);
		when(Converters.convert(Optional.of(example))).thenReturn(xmlExampleEntityMock);

		XmlExampleEntity xmlExampleEntity = underTest.getEntity(EXAMPLE_ID);
		verifyStatic();

		assertThat(xmlExampleEntity.getId()).isEqualTo(EXAMPLE_ID);
		assertThat(xmlExampleEntity.getName()).isEqualTo(EXAMPLE_NAME);

	}
}
