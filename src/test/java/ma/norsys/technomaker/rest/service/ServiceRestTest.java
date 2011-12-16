package ma.norsys.technomaker.rest.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import ma.norsys.technomaker.rest.dao.IDaoRest;
import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;

import org.junit.Before;
import org.junit.Test;

public class ServiceRestTest {
	private IServiceRest service;
	private IDaoRest dao;

	@Before
	public void setUp() {
		dao = mock(IDaoRest.class, "dao");
		service = new ServiceRest(dao);
	}

	/**
	 * test si le service est différent de null
	 */
	@Test
	public void daoAndserviceNotNullTest() {
		assertNotNull(dao);
		assertNotNull(service);
	}


	/**
	 * test si la queue a été supprimée
	 */
	@Test
	public void supprimerQueueTest() {
		String id = "id";
		when(dao.deleteQueue(id)).thenReturn(true);
		String result = service.supprimerQueue(id);
		assertNotNull(result);
	}

	/**
	 * test lecture  d'une queue
	 */
	@Test
	public void lireQueueTest() {
		String idQueue = "test";
		Queue queue = new Queue();
		when(dao.readQueue(idQueue)).thenReturn(queue);
		String result = service.lireQueue(idQueue,"");
		assertNotNull(result);
	}
	/**
	 * test liste des queues
	 */
	@Test
	public void listerQueueTest() {
		when(dao.listQueue()).thenReturn(new ArrayList<Queue>());
		String result = service.listerQueue();
		assertNotNull(result);
	}

	/**
	 * test si le message a été ajouté
	 */
	@Test
	public void ajouterMessageTest() {
		String id = "id";
		String objet = "objet";
		String contenu = "contenu";
		Message message = new Message(id, objet, contenu);
		when(dao.createMessage(message, id)).thenReturn(message);
		String result = service.ajouterMessage(message.getIdMessage(),message.getObjetMessage(),message.getContenuMessage(), id);
		assertNotNull(result);
	}

	/**
	 * test si le message a été modifié
	 */
	@Test
	public void modifierMessageTest() {
		String id = "id";
		String objet = "objet";
		String contenu = "contenu";
		Message messageModif = new Message(id, objet + "M", contenu + "M");
		when(dao.updateMessage(messageModif, id)).thenReturn(messageModif);
		String result = service.modifierMessage(id, id);
		assertNotNull(result);
	}

	/**
	 * test si le message a été lu
	 */
	@Test
	public void lireMessageTest() {
		String id = "id";
		String objet = "objet";
		String contenu = "contenu";
		Message message = new Message(id, objet, contenu);
		when(dao.readMessage(id, id)).thenReturn(message);
		String result = service.lireMessage(id, id);
		assertNotNull(result);
	}

	/**
	 * test si le message a été supprimé
	 */
	@Test
	public void supprimerMessageTest() {
		String id = "id";
		when(dao.deleteMessage(id, id)).thenReturn(true);
		String result = service.supprimerMessage(id, id);
		assertNotNull(result);
	}
}
