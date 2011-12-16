package ma.norsys.technomaker.rest.util;

import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_PATH_TEST;

import java.io.IOException;
import java.util.List;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;

import org.jdom.JDOMException;
import org.junit.Before;
import org.junit.Test;

public class SaxSingletonTest extends TestCase {
	private SaxSingleton singleton = null;

	public SaxSingletonTest(String string) {
		super(string);
	}

	// ordonner les testes unitaires
	public static junit.framework.Test suite() {
		TestSuite suite = new TestSuite("Test SaxSingletonTest");
		suite.addTest(new SaxSingletonTest("singletonNotNullTest"));
		suite.addTest(new SaxSingletonTest("createXmlFileTest"));
		suite.addTest(new SaxSingletonTest("addQueueToXmlFileTest"));
		suite.addTest(new SaxSingletonTest("listQueueToXmlFileTest"));
		suite.addTest(new SaxSingletonTest("addMessageToQueueTest"));
		suite.addTest(new SaxSingletonTest("updateMessageInQueueTest"));
		suite.addTest(new SaxSingletonTest("getMessageInQueueTest"));
		suite.addTest(new SaxSingletonTest("deleteMessageInQueueTest"));
		suite.addTest(new SaxSingletonTest("deleteQueueInXmlFileTest"));
		return suite;
	}

	@Before
	public void setUp() throws Exception {
		singleton = SaxSingleton.getInstance(XML_FILE_PATH_TEST);
	}

	/**
	 * test si le singleton est différent de null
	 */
	@Test
	public void singletonNotNullTest() {
		assertNotNull(singleton);
	}

	/**
	 * test si tout se passe bien lors de la création d'un fichier XML
	 */
	@Test
	public void createXmlFileTest() {
		boolean bool = singleton.createOrUpdateXmlFile();
		assertTrue("bool = true alors le fichier est MAJ", bool);
	}

	@Test
	public void addQueueToXmlFileTest() {
		Queue queue = new Queue("123460");
		boolean bool = singleton.addQueueToXmlFile(queue);
		assertTrue("bool = true alors la queue a été ajouté", bool);
	}

	@Test()
	public void listQueueToXmlFileTest() {
		List<Queue> liste = singleton.listQueueToXmlFile();
		assertNotNull(liste);
		assertEquals(1, liste.size());
		assertEquals("123460", liste.get(0).getIdQueue());
	}

	@Test
	public void addMessageToQueueTest() throws IOException, JDOMException {
		String idQueue = "123460";
		Message message = new Message("123456A", "objetAdded", "contenuAdded");
		boolean bool = singleton.addOrUpdateMessageInQueue(idQueue, message);
		assertTrue("bool = true alors le message a été ajouté ", bool);
	}

	@Test
	public void updateMessageInQueueTest() throws IOException, JDOMException {
		String idQueue = "123460";
		Message message = new Message("123456A", "objetUpdate", "contenuUpdate");
		boolean bool = singleton.addOrUpdateMessageInQueue(idQueue, message);
		assertTrue("bool = true alors le message a été modifié", bool);
	}

	@Test
	public void getMessageInQueueTest() throws IOException, JDOMException {
		String idQueue = "123460";
		String idMessage = "123456A";
		String contenuUpdate = "contenuUpdate";
		Message message = singleton.getMessageInQueue(idQueue, idMessage);
		assertNotNull(message);
		assertEquals(contenuUpdate, message.getContenuMessage());
	}

	@Test
	public void getQueueInXmlFileTest() throws IOException, JDOMException {
		String idQueue = "123460";
		String objetUpdate = "objetUpdate";
		Queue queue = singleton.getQueueInXmlFile(idQueue);
		assertNotNull(queue);
		assertEquals(idQueue, queue.getIdQueue());
		assertNotNull(queue.getLesMessages());
		assertEquals(objetUpdate, queue.getLesMessages().iterator().next()
				.getObjetMessage());
	}

	@Test
	public void deleteMessageInQueueTest() throws IOException, JDOMException {
		String idQueue = "123460";
		String idMessage = "123456A";
		boolean bool = singleton.deleteMessageInQueue(idQueue, idMessage);
		assertTrue("bool = true alors la queue a été supprimé", bool);
	}

	@Test
	public void deleteQueueInXmlFileTest() throws IOException, JDOMException {
		String idQueue = "123460";
		boolean bool = singleton.deleteQueueInXmlFile(idQueue);
		assertTrue("bool = true alors la queue a été supprimée", bool);
	}

}
