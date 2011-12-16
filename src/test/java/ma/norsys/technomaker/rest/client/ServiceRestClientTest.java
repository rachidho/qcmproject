package ma.norsys.technomaker.rest.client;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Before;
import org.junit.Test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.representation.Form;

public class ServiceRestClientTest extends TestCase{

	ClientConfig config;
	Client client;
	WebResource service;
	public ServiceRestClientTest(String string) {
		super(string);
	}

	// ordonner les testes unitaires
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite("Test ServiceRestClientTest");
		suite.addTest(new ServiceRestClientTest("ajouterQueueTest"));
		suite.addTest(new ServiceRestClientTest("listerQueueTest"));
		suite.addTest(new ServiceRestClientTest("supprimerQueueTest"));
		
		return suite;
	}
	private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"http://localhost:8080/tpvalidation-rest/queue/").build();
	}

	@Before
	public void setUp() {
		config = new DefaultClientConfig();
		client = Client.create(config);
		service = client.resource(getBaseURI());
	}
	@Test
	public void supprimerQueueTest() {
		String teste = "queueTest";
		ClientResponse clientResponse = service.path("servicerest")
				.path("deletequeue").path(teste)
				.accept(MediaType.TEXT_HTML)
				.get(ClientResponse.class);
		assertNotNull(clientResponse.getEntity(String.class));
	}
	@Test
	public void ajouterQueueTest() {
		Form form = new Form();
		form.add("id", "queueTest");
		ClientResponse clientResponse = service.path("servicerest")
				.path("createqueue")
				.accept(MediaType.APPLICATION_FORM_URLENCODED)
				.post(ClientResponse.class, form);
		assertNotNull(clientResponse.getEntity(String.class));
	}

	@Test
	public void listerQueueTest() {
		String result = service.path("servicerest").path("listqueue")
				.accept(MediaType.TEXT_HTML).get(String.class);
		assertNotNull(result);
	}
}
