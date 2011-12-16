package ma.norsys.technomaker.rest.dao;

import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_PATH_TEST;

import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;

import org.junit.Before;
import org.junit.Test;

public class DaoRestTest extends TestCase {
	private IDaoRest dao = null;

	public DaoRestTest(String string) {
		super(string);
	}

	// ordonner les testes unitaires
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite("Test DaoRestTest");
		suite.addTest(new DaoRestTest("daoNotNullTest"));
		suite.addTest(new DaoRestTest("createQueueTest"));
		suite.addTest(new DaoRestTest("readQueueTest"));
		suite.addTest(new DaoRestTest("listQueueTest"));
		suite.addTest(new DaoRestTest("createMessageTest"));
		suite.addTest(new DaoRestTest("updateMessageTest"));
		suite.addTest(new DaoRestTest("readMessageTest"));
		suite.addTest(new DaoRestTest("deleteMessageTest"));
		suite.addTest(new DaoRestTest("deleteQueueTest"));
		return suite;
	}

	@Before
	public void setUp() throws Exception {
		dao = new DaoRest(XML_FILE_PATH_TEST);
	}

	/**
	 * test si le dao est différent de null
	 */
	@Test
	public void daoNotNullTest() {
		assertNotNull(dao);
	}

	/**
	 * test si l'ajout d'une queue est OK
	 */
	@Test
	public void createQueueTest() {
		String idQueue = "codeTest";
		Queue queue = new Queue(idQueue);
		Queue result = dao.createQueue(queue);
		assertNotNull(result);
		assertEquals(idQueue, result.getIdQueue());
	}

	/**
	 * test lecture de la queue ajoutée
	 */
	@Test
	public void readQueueTest() {
		String idQueue = "codeTest";
		Queue result = dao.readQueue(idQueue);
		assertNotNull(result);
		assertEquals(idQueue, result.getIdQueue());
	}
	/**
	 * test liste des queues
	 */
	@Test
	public void listQueueTest() {
		String idQueue = "codeTest";
		List<Queue> result = dao.listQueue();
		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(idQueue, result.get(0).getIdQueue());
	}
	
	/**
	 * test si l'ajout d'un message est OK
	 */
	@Test
	public void createMessageTest() {
		String idQueue = "codeTest";
		String idMessage = "idMessage";
		Message message = new Message(idMessage, "objetAdded", "contenuAdded");
		Message result = dao.createMessage(message, idQueue);
		assertNotNull(result);
		assertEquals(idMessage, result.getIdMessage());
	}

	/**
	 * test si l'ajout d'un message est OK
	 */
	@Test
	public void updateMessageTest() {
		String idQueue = "codeTest";
		String idMessage = "idMessage";
		Message message = new Message(idMessage, "objetUpdated",
				"contenuUpdated");
		Message result = dao.updateMessage(message, idQueue);
		assertNotNull(result);
		assertEquals(idMessage, result.getIdMessage());
	}

	/**
	 * test la lecture du message ajouté
	 */
	@Test
	public void readMessageTest() {
		String idQueue = "codeTest";
		String idMessage = "idMessage";
		Message result = dao.readMessage(idMessage, idQueue);
		assertNotNull(result);
		assertEquals("objetUpdated", result.getObjetMessage());
	}

	/**
	 * test la suppression d'un message message donné
	 */
	@Test
	public void deleteMessageTest() {
		String idQueue = "codeTest";
		String idMessage = "idMessage";
		boolean result = dao.deleteMessage(idMessage, idQueue);
		assertTrue(result);
	}

	/**
	 * test suppression d'une queue donnée
	 */
	@Test
	public void deleteQueueTest() {
		String idQueue = "codeTest";
		boolean result = dao.deleteQueue(idQueue);
		assertTrue(result);
	}
}
