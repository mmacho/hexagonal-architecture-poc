
package com.hexagonal.infra.adapter.api;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Singleton;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hexagonal.core.port.ExampleServicePort;
import com.hexagonal.infra.adapter.api.model.XmlExampleEntity;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@Service
@Singleton
@WebService(endpointInterface = "com.hexagonal.infra.adapter.api.ExampleEntityEndpoint", serviceName = ExampleEntityEndpointConstants.SERVICE, targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
public class CodeFirstExampleEntityEndpoint implements ExampleEntityEndpoint {

	private final ExampleServicePort jpa;

	private final ExampleServicePort jdbc;

	@Autowired
	public CodeFirstExampleEntityEndpoint(@Qualifier("exampleJPAServiceAdapter") ExampleServicePort jpa,
			@Qualifier("exampleJDBCServiceAdapter") ExampleServicePort jdbc) {
		super();
		this.jpa = jpa;
		this.jdbc = jdbc;
	}

	@Override
	public final XmlExampleEntity getEntity(Integer id) {
		checkNotNull(id, "Received a null pointer as id");
		log.debug(String.format("Received jpa request for id %d", id));
		XmlExampleEntity xmlExampleEntity = Converters.convert(jpa.getExampleById(id));
		return xmlExampleEntity;
	}

	@Override
	public final XmlExampleEntity getJdbcEntity(Integer id) {
		checkNotNull(id, "Received a null pointer as id");
		log.debug(String.format("Received jdbc request for id %d", id));
		return jdbc.getExampleById(id).map(Converters::convert).orElseThrow(ExampleNotFound::new);
	}

	static class ExampleNotFound extends RuntimeException {

		private static final long serialVersionUID = -8207618205988260273L;

	}
}
