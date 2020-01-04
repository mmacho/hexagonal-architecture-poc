
package com.hexagonal.infra.adapter.api;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Singleton;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.hexagonal.core.port.ExampleServicePort;
import com.hexagonal.infra.adapter.api.model.XmlExampleEntity;

/**
 * Primary Adapter, module containing primary adapters (which implement primary
 * ports) – these module translates calls from other systems (other applications
 * or just GUI) to call logic from our domain
 * 
 * This is module, which should contain technical information about calling our
 * domain. In our case we have endpoint providing soap resource returning our
 * examples
 * 
 * In this place we could map our domain model to specific response.
 * 
 * @author Conchi
 *
 */
@Singleton
@WebService(endpointInterface = "com.hexagonal.infra.adapter.api.ExampleEntityEndpoint", serviceName = ExampleEntityEndpointConstants.SERVICE, targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
public class CodeFirstExampleEntityEndpoint implements ExampleEntityEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeFirstExampleEntityEndpoint.class);

	private final ExampleServicePort jpa;

	private final ExampleServicePort jdbc;

	@Autowired
	public CodeFirstExampleEntityEndpoint(@Qualifier("examplePersistencePort") ExampleServicePort jpa,
			@Qualifier("examplePersistencePort2") ExampleServicePort jdbc) {
		super();
		this.jpa = jpa;
		this.jdbc = jdbc;
	}

	@Override
	public final XmlExampleEntity getEntity(Integer id) {
		checkNotNull(id, "Received a null pointer as id");
		LOGGER.debug(String.format("Received request for id %d", id));
		XmlExampleEntity xmlExampleEntity = Converters.convert(jpa.getExampleById(id));
		return xmlExampleEntity;
	}

	@Override
	public final XmlExampleEntity getJdbcEntity(Integer id) {
		checkNotNull(id, "Received a null pointer as id");
		LOGGER.debug(String.format("Received request for id %d", id));
		XmlExampleEntity xmlExampleEntity = Converters.convert(jdbc.getExampleById(id));
		return xmlExampleEntity;
	}

}
