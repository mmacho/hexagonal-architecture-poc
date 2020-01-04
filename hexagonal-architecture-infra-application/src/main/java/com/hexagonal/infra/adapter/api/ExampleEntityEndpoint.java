
package com.hexagonal.infra.adapter.api;

import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.cxf.annotations.WSDLDocumentation;
import org.apache.cxf.annotations.WSDLDocumentationCollection;

import com.hexagonal.infra.adapter.api.model.XmlExampleEntity;

/**
 * Primary Port
 * 
 * @author Conchi
 *
 */
@WebService(serviceName = ExampleEntityEndpointConstants.SERVICE, targetNamespace = ExampleEntityEndpointConstants.ENTITY_NS)
@WSDLDocumentationCollection({ @WSDLDocumentation("The only portType"),
		@WSDLDocumentation(value = "Web service for testing WSS", placement = WSDLDocumentation.Placement.TOP),
		@WSDLDocumentation(value = "Web service binding", placement = WSDLDocumentation.Placement.BINDING) })
public interface ExampleEntityEndpoint {

	@WSDLDocumentation("Returns the entity with the specified id")
	public XmlExampleEntity getEntity(@WebParam(name = "id") final Integer id);

	@WSDLDocumentation("Returns the entity with the specified id implemented with jdbc")
	public XmlExampleEntity getJdbcEntity(@WebParam(name = "id") final Integer id);

}
