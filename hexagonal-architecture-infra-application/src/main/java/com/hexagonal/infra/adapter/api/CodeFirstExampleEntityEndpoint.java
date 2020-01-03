
package com.hexagonal.infra.adapter.api;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Optional;

import javax.inject.Singleton;
import javax.jws.WebService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.hexagonal.domain.example.Example;
import com.hexagonal.domain.example.ExampleService;
import com.hexagonal.infra.adapter.api.model.XmlExampleEntity;

@Singleton
@WebService(endpointInterface = "com.wandrell.example.mule.wss.endpoint.ExampleEntityEndpoint", serviceName = ExampleEntityEndpointConstants.SERVICE, targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
public class CodeFirstExampleEntityEndpoint implements ExampleEntityEndpoint {

	private static final Logger LOGGER = LoggerFactory.getLogger(CodeFirstExampleEntityEndpoint.class);

	private final ExampleService exampleService;

	@Autowired
	public CodeFirstExampleEntityEndpoint(final ExampleService exampleService) {
		super();
		this.exampleService = exampleService;
	}

	@Override
	public final XmlExampleEntity getEntity(final Integer id) {
		final XmlExampleEntity response; // XML response with the entity data
		Example example = null;

		checkNotNull(id, "Received a null pointer as id");

		LOGGER.debug(String.format("Received request for id %d", id));

		// Acquires the domain
		Optional<Example> op = getExampleService().findById(id);
		if (op.isPresent()) {
			example = op.get();
			LOGGER.debug(String.format("Found domain with id %1$d", example.getId()));
		}

		response = new XmlExampleEntity();
		BeanUtils.copyProperties(example, response);
		return response;
	}

	public ExampleService getExampleService() {
		return exampleService;
	}

}
