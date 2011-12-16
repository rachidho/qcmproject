package ma.norsys.technomaker.rest.util;

import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ELEMENT_CONTENU;
import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ELEMENT_ID_MESSAGE;
import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ELEMENT_ID_QUEUE;
import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ELEMENT_MESSAGE;
import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ELEMENT_MESSAGES;
import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ELEMENT_OBJET;
import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ELEMENT_QUEUE;
import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_ROOT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class SaxSingleton {
	// logger pour la trace d'execution
	static final Logger LOGGER = Logger.getLogger(SaxSingleton.class);

	private static volatile SaxSingleton instance = null;
	private static String filePath = null;

	private SaxSingleton() {
		super();
	}

	public final static SaxSingleton getInstance(String pFilePath) {
		if (null == SaxSingleton.instance) {
			SaxSingleton.instance = new SaxSingleton();
		}
		filePath = pFilePath;
		return SaxSingleton.instance;
	}

	/**
	 * Création d'un fichier XML vide
	 * 
	 * @return
	 */
	public boolean createOrUpdateXmlFile() {
		Document document = getDocument();
		saveXmlFile(document);
		return (null != document);
	}
	
	/**
	 * L'ajout d'une queue dans le fichier XML
	 * 
	 * @param queueVal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addQueueToXmlFile(Queue queueVal) {
		LOGGER.debug("addQueueToXmlFile(Queue " + queueVal + ")");
		boolean notExists = true;
		
		Document document = getDocument();
		List<Element> listeQueue = document.getRootElement().getChildren();
		for (Element element : listeQueue) {
			LOGGER.debug(XML_FILE_ELEMENT_ID_QUEUE + " : "
					+ element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue());
			if (queueVal.getIdQueue().equals(element.getChild(XML_FILE_ELEMENT_ID_QUEUE)
					.getValue())) {
				notExists = false;
				break;
			}
		}
		if(notExists){
			Element queue = new Element(XML_FILE_ELEMENT_QUEUE);
			Element idQueue = new Element(XML_FILE_ELEMENT_ID_QUEUE);
			idQueue.setText(queueVal.getIdQueue());
			Element messages = new Element(XML_FILE_ELEMENT_MESSAGES);
			queue.addContent(idQueue);
			queue.addContent(messages);
			document.getRootElement().addContent(queue);
		}
		return saveXmlFile(document);

	}
	@SuppressWarnings("unchecked")
	public List<Queue> listQueueToXmlFile() {
		LOGGER.debug("listQueueToXmlFile()");
		Document document = getDocument();
		List<Queue> listeRetour = new ArrayList<Queue>();
		List<Element> listeQueue = document.getRootElement().getChildren();
		LOGGER.debug("listeQueue.size():"+listeQueue.size());
		for (Element element : listeQueue) {
			LOGGER.debug(XML_FILE_ELEMENT_ID_QUEUE + " : " + element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue());
			listeRetour.add(new Queue(element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue()));
		}
		LOGGER.debug("listQueueToXmlFile():FIN");
		return listeRetour;

	}
	/**
	 * Supprimer une queue donnée
	 * 
	 * @param idQueue
	 *            identifiant de la queue
	 * @return boolean
	 */
	@SuppressWarnings("unchecked")
	public boolean deleteQueueInXmlFile(String idQueue) {
		LOGGER.debug("deleteQueueInXmlFile( String " + idQueue + ")");
		Document document = getDocument();
		List<Element> listeQueue = document.getRootElement().getChildren();
		for (Element element : listeQueue) {
			LOGGER.debug(XML_FILE_ELEMENT_ID_QUEUE + " : "
					+ element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue());
			if (idQueue.equals(element.getChild(XML_FILE_ELEMENT_ID_QUEUE)
					.getValue())) {
				listeQueue.remove(element);
				break;
			}
		}

		return saveXmlFile(document);

	}

	/**
	 * Pour récupérer une queue donnée
	 * 
	 * @param idQueue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Queue getQueueInXmlFile(String idQueue) {
		LOGGER.debug("getQueueInXmlFile( String " + idQueue + ")");
		Document document = getDocument();
		List<Element> listeQueue = document.getRootElement().getChildren();
		Queue queue = new Queue();
		for (Element element : listeQueue) {
			LOGGER.debug(XML_FILE_ELEMENT_ID_QUEUE + " : "
					+ element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue());
			if (idQueue.equals(element.getChild(XML_FILE_ELEMENT_ID_QUEUE)
					.getValue())) {
				// l'identifiant de la queue
				queue.setIdQueue(element.getChild(XML_FILE_ELEMENT_ID_QUEUE)
						.getValue());
				// chargement la liste des messages
				List<Element> listeMessage = element.getChild(
						XML_FILE_ELEMENT_MESSAGES).getChildren();
				java.util.Queue<Message> lesMessages = new LinkedList<Message>();
				Message message;
				for (Element elementMsg : listeMessage) {
					message = new Message(elementMsg.getChild(
							XML_FILE_ELEMENT_ID_MESSAGE).getValue(),
							elementMsg.getChild(XML_FILE_ELEMENT_OBJET).getValue(),
							elementMsg.getChild(XML_FILE_ELEMENT_CONTENU).getValue());
					lesMessages.add(message);

				}
				queue.setLesMessages(lesMessages);
				break;
			}
		}

		return queue;

	}

	/**
	 * ajouter un message dans une queue
	 * 
	 * @param queueVal
	 * @param messageVal
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean addOrUpdateMessageInQueue(String idQueue, Message messageVal) {
		LOGGER.debug("addOrUpdateMessageInQueue( String " + idQueue
				+ ", Message " + messageVal + ")");
		boolean existe = false;
		Document document = getDocument();
		List<Element> listeQueue = document.getRootElement().getChildren();
		for (Element element : listeQueue) {
			LOGGER.debug(XML_FILE_ELEMENT_ID_QUEUE + " : "
					+ element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue());

			if (idQueue.equals(element.getChild(XML_FILE_ELEMENT_ID_QUEUE)
					.getValue())) {
				// les éléments XML d'un message
				Element elementMessage;
				Element elementIdMsg;
				Element elementObjetMsg;
				Element elementContenuMsg;

				List<Element> listeMessage = element.getChild(
						XML_FILE_ELEMENT_MESSAGES).getChildren();
				for (Element elementMsg : listeMessage) {
					// teste si le message existe
					if (messageVal.getIdMessage().equals(
							elementMsg.getChild(XML_FILE_ELEMENT_ID_MESSAGE)
									.getValue())) {
						existe = true;
						break;
					}
				}
				if (existe) {
					elementMessage = element
							.getChild(XML_FILE_ELEMENT_MESSAGES).getChild(
									XML_FILE_ELEMENT_MESSAGE);
					elementMessage.getChild(XML_FILE_ELEMENT_OBJET).setText(
							messageVal.getObjetMessage());
					elementMessage.getChild(XML_FILE_ELEMENT_CONTENU).setText(
							messageVal.getContenuMessage());
				} else {
					elementMessage = new Element(XML_FILE_ELEMENT_MESSAGE);
					elementIdMsg = new Element(XML_FILE_ELEMENT_ID_MESSAGE);
					elementObjetMsg = new Element(XML_FILE_ELEMENT_OBJET);
					elementContenuMsg = new Element(XML_FILE_ELEMENT_CONTENU);
					elementIdMsg.setText(messageVal.getIdMessage());
					elementObjetMsg.setText(messageVal.getObjetMessage());
					elementContenuMsg.setText(messageVal.getContenuMessage());
					elementMessage.addContent(elementIdMsg);
					elementMessage.addContent(elementObjetMsg);
					elementMessage.addContent(elementContenuMsg);
					element.getChild(XML_FILE_ELEMENT_MESSAGES).addContent(
							elementMessage);
				}
				break;
			}
		}
		return saveXmlFile(document);

	}

	/**
	 * Méthode pour supprimer un message donné dans une queue donnée
	 * 
	 * @param idQueue
	 * @param idMessage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public boolean deleteMessageInQueue(String idQueue, String idMessage) {
		LOGGER.debug("deleteMessageInQueue( String " + idQueue + ", String "
				+ idMessage + ")");
		Document document = getDocument();
		List<Element> listeQueue = document.getRootElement().getChildren();
		for (Element element : listeQueue) {
			LOGGER.debug(XML_FILE_ELEMENT_ID_QUEUE + " : "
					+ element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue());
			if (idQueue.equals(element.getChild(XML_FILE_ELEMENT_ID_QUEUE)
					.getValue())) {
				List<Element> listeMessage = element.getChild(
						XML_FILE_ELEMENT_MESSAGES).getChildren();
				for (Element elementMsg : listeMessage) {
					// teste si le message existe
					if (idMessage.equals(elementMsg.getChild(
							XML_FILE_ELEMENT_ID_MESSAGE).getValue())) {
						listeMessage.remove(elementMsg);
						break;
					}
				}
				break;
			}
		}
		return saveXmlFile(document);

	}
	/**
	 * Récupérer un message donnée
	 * @param idQueue
	 * @param idMessage
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Message getMessageInQueue(String idQueue, String idMessage) {
		LOGGER.debug("getMessageInQueue( String " + idQueue + ", String "
				+ idMessage + ")");
		Document document = getDocument();
		List<Element> listeQueue = document.getRootElement().getChildren();
		Message message = null;
		for (Element element : listeQueue) {
			LOGGER.debug(XML_FILE_ELEMENT_ID_QUEUE + " : "
					+ element.getChild(XML_FILE_ELEMENT_ID_QUEUE).getValue());
			if (idQueue.equals(element.getChild(XML_FILE_ELEMENT_ID_QUEUE)
					.getValue())) {
				List<Element> listeMessage = element.getChild(
						XML_FILE_ELEMENT_MESSAGES).getChildren();
				for (Element elementMsg : listeMessage) {
					// teste si le message existe
					if (idMessage.equals(elementMsg.getChild(
							XML_FILE_ELEMENT_ID_MESSAGE).getValue())) {
						listeMessage.remove(elementMsg);
						message = new Message(elementMsg.getChild(
								XML_FILE_ELEMENT_ID_MESSAGE).getValue(), elementMsg.getChild(
										XML_FILE_ELEMENT_OBJET).getValue(), elementMsg.getChild(
												XML_FILE_ELEMENT_CONTENU).getValue());
						break;
					}
				}
				break;
			}
		}
		return message;

	}
	/**
	 * Méthode pour récupérer le document XML en fonction de son chemin
	 * 
	 * @return
	 */
	private Document getDocument() {
		Document document = null;
		SAXBuilder sxb = new SAXBuilder();
		File file = new File(filePath);
		LOGGER.debug(">>> getAbsolutePath : " + file.getAbsolutePath());
		if (file.exists()) {
			try {
				document = sxb.build(file);
			} catch (JDOMException e) {
				LOGGER.debug(">>> JDOMException : " + e.getMessage());
			} catch (IOException e) {
				LOGGER.debug(">>> IOException : " + e.getMessage());
			}
		} else {
			document = new Document(new Element(XML_FILE_ROOT));
		}
		return document;

	}

	/**
	 * Méthode pour sauvegarder le fichier XML
	 * 
	 * @param document
	 * @param filePath
	 * @return
	 */
	private boolean saveXmlFile(Document document) {
		LOGGER.debug("saveXmlFile(Document " + document + ")");
		XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
		boolean bool = false;
		try {
			sortie.output(document, new FileOutputStream(filePath));
			bool = true;
		} catch (FileNotFoundException e) {
			LOGGER.debug(">>> FileNotFoundException : " + e.getMessage());
		} catch (IOException e) {
			LOGGER.debug(">>> IOException : " + e.getMessage());
		}
		return bool;
	}

}
