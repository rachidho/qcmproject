package ma.norsys.technomaker.rest.service;

import static ma.norsys.technomaker.rest.util.Constants.XML_FILE_PATH_MAIN;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ma.norsys.technomaker.rest.dao.DaoRest;
import ma.norsys.technomaker.rest.dao.IDaoRest;
import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;

import org.apache.log4j.Logger;

@Path("/servicerest")
public class ServiceRest implements IServiceRest {
	// logger pour la trace d'execution
	static final Logger LOGGER = Logger.getLogger(ServiceRest.class);
	private IDaoRest dao;

	public ServiceRest() {
		super();
		this.dao = new DaoRest(XML_FILE_PATH_MAIN);

	}

	public ServiceRest(IDaoRest dao) {
		super();
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.service.IServiceRest#ajouterQueue(ma.norsys
	 * .technomaker.rest.domain.Queue)
	 */
	@POST
	@Path("createqueue")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String ajouterQueue(@FormParam("id") String id) {
		LOGGER.debug(">> ajouterQueue(@PathParam String " + id + ")");
		StringBuffer codeRetour = new StringBuffer();
		id = (id.isEmpty()) ? "idDefault" : id;
		Queue queue = new Queue(id);
		String idRetour = dao.createQueue(queue).getIdQueue();
		LOGGER.debug(">> Ajout id = " + idRetour + ")");
		codeRetour
				.append("<META http-equiv='refresh' content='0; URL=/tpvalidation-rest/queue/servicerest/listqueue'>");
		return codeRetour.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.service.IServiceRest#supprimerQueue(java.lang
	 * .String)
	 */
	@GET
	@Path("deletequeue/{id}")
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String supprimerQueue(@PathParam("id") String idQueue) {
		LOGGER.debug(">> supprimerQueue(@PathParam String " + idQueue + ")");
		StringBuffer codeRetour = new StringBuffer();
		boolean ok = dao.deleteQueue(idQueue);
		LOGGER.debug(">> Suppression = " + ok + ")");
		codeRetour
				.append("<META http-equiv='refresh' content='0; URL=/tpvalidation-rest/queue/servicerest/listqueue'>");
		return codeRetour.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.service.IServiceRest#ajouterMessage(ma.norsys
	 * .technomaker.rest.domain.Message, java.lang.String)
	 */
	@POST
	@Path("createmessage")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String ajouterMessage(@FormParam("idM") String idMessage,
			@FormParam("objet") String objetMessage,
			@FormParam("contenu") String contenuMessage,
			@FormParam("id") String idQueue) {
		LOGGER.debug(">> ajouterMessage ");
		idMessage = (idMessage.isEmpty()) ? "idDefault" : idMessage;
		objetMessage = (objetMessage.isEmpty()) ? "objetDefault" : objetMessage;
		contenuMessage = (contenuMessage.isEmpty()) ? "contenuDefault"
				: contenuMessage;
		StringBuffer codeRetour = new StringBuffer();
		Message message = new Message(idMessage, objetMessage, contenuMessage);
		dao.createMessage(message, idQueue);
		codeRetour
				.append("<META http-equiv='refresh' content='0; URL=/tpvalidation-rest/queue/servicerest/readqueue/que-"
						+ idQueue + "-msg-0'>");
		return codeRetour.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.service.IServiceRest#lireMessage(java.lang
	 * .String, java.lang.String)
	 */
	@GET
	@Path("readmessage/msg-{msg}-que-{que}")
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String lireMessage(@PathParam("msg") String idMessage,
			@PathParam("que") String idQueue) {
		LOGGER.debug(">> lireMessage ");
		StringBuffer codeRetour = new StringBuffer();
		codeRetour.append("<h4>Lecture message</h4>");
		Message message = dao.readMessage(idMessage, idQueue);
		dao.deleteMessage(idMessage, idQueue);
		codeRetour
				.append("<b>Id message : </b>" + message.getIdMessage()
						+ "<br>")
				.append("<b>Objet message : </b>" + message.getObjetMessage()
						+ "<br>")
				.append("<b>Contenu message : </b>"
						+ message.getContenuMessage() + "<br>")
				.append("<a href='/tpvalidation-rest/queue/servicerest/readqueue/que-"
						+ idQueue + "-msg-0'>retour</a>");
		return codeRetour.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.service.IServiceRest#modifierMessage(ma.norsys
	 * .technomaker.rest.domain.Message, java.lang.String)
	 */
	@GET
	@Path("updatemessage/que-{que}-msg-{msg}")
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String modifierMessage(@PathParam("msg") String idMessage,
			@PathParam("que") String idQueue) {
		LOGGER.debug(">> modifierMessage ");
		StringBuffer codeRetour = new StringBuffer();
		codeRetour
				.append("<META http-equiv='refresh' content='0; URL=/tpvalidation-rest/queue/servicerest/readqueue/que-"
						+ idQueue + "-msg-" + idMessage + "'>");
		return codeRetour.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.service.IServiceRest#supprimerMessage(java
	 * .lang.String, java.lang.String)
	 */
	@GET
	@Path("deletemessage/msg-{msg}-que-{que}")
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String supprimerMessage(@PathParam("msg") String idMessage,
			@PathParam("que") String idQueue) {
		LOGGER.debug(">> supprimerMessage ");
		StringBuffer codeRetour = new StringBuffer();
		dao.deleteMessage(idMessage, idQueue);
		codeRetour
				.append("<META http-equiv='refresh' content='0; URL=/tpvalidation-rest/queue/servicerest/readqueue/que-"
						+ idQueue + "-msg-0'>");
		return codeRetour.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ma.norsys.technomaker.rest.service.IServiceRest#listerQueue()
	 */
	@GET
	@Path("listqueue")
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String listerQueue() {
		StringBuffer codeRetour = new StringBuffer();
		codeRetour.append("<h1>La liste des queues</h1>");

		List<Queue> liste = dao.listQueue();
		if (0 == liste.size()) {
			codeRetour.append("<h4>liste des queues est vide<h4>");
		} else {
			codeRetour.append("<table border=1>").append(
					"<tr><td>id queue </td><td>actions</td</tr>");
			for (Queue queue : liste) {
				codeRetour
						.append("<tr><td><b>" + queue.getIdQueue())
						.append("</b></td><td><a href='/tpvalidation-rest/queue/servicerest/readqueue/que-"
								+ queue.getIdQueue() + "-msg-0'")
						.append("'>afficher</a> ")
						.append("<a href='/tpvalidation-rest/queue/servicerest/deletequeue/")
						.append(queue.getIdQueue()
								+ "'>supprimer</a></td></tr>");
			}
			codeRetour.append("</table>");
		}
		codeRetour
				.append("<h4>ajouter une queue </h4>")
				.append("<form action='/tpvalidation-rest/queue/servicerest/createqueue' method='post'>")
				.append("Id queue : <input name='id' type='text'/>")
				.append("<input type='submit' value='valider'>")
				.append("</form>");
		return codeRetour.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.service.IServiceRest#lireQueue(java.lang.String
	 * )
	 */
	@GET
	@Path("readqueue/que-{id}-msg-{idM}")
	@Produces(MediaType.TEXT_HTML)
	@Override
	public String lireQueue(@PathParam("id") String idQueue,
			@DefaultValue("0") @PathParam("idM") String idMessage) {
		StringBuffer codeRetour = new StringBuffer();
		Queue queue = dao.readQueue(idQueue);
		codeRetour.append("<h2>La liste des messages pour la queue : "
				+ queue.getIdQueue() + "</h2>");
		java.util.Queue<Message> messages = queue.getLesMessages();
		if (null != messages && 0 == messages.size()) {
			codeRetour.append("<h4>queue vide<h4>");
		} else if (null != messages) {
			codeRetour
					.append("<table border=1>")
					.append("<tr><td>id message</td><td>objet</td><td>contenu</td><td>actions</td></tr>");
			boolean firstEntry = true;
			for (Message message : messages) {
				codeRetour.append("<tr><td>" + message.getIdMessage()
						+ "</td><td>" + message.getObjetMessage() + "</td><td>"
						+ message.getContenuMessage() + "</td><td>");

				if (firstEntry) {
					firstEntry = false;
					codeRetour
							.append("<a href='/tpvalidation-rest/queue/servicerest/readmessage/msg-")
							.append(message.getIdMessage() + "-que-" + idQueue
									+ "'>afficher</a>");
				} else {
					codeRetour
							.append("<a href='/tpvalidation-rest/queue/servicerest/updatemessage/que-")
							.append(queue.getIdQueue() + "-msg-"
									+ message.getIdMessage()
									+ "'>modifier</a> ");
				}
				codeRetour.append("</td></tr>");
			}
			codeRetour.append("</table>");
		}
		Message messageForm = dao.readMessage(idMessage, idQueue);
		String readonly = "readonly='readonly'";
		String edit = "modifier";
		if (null == messageForm) {
			messageForm = new Message("", "", "");
			readonly = "";
			edit = "ajouter";
		}

		codeRetour
				.append("<h4>" + edit + " un message </h4>")
				.append("<form action='/tpvalidation-rest/queue/servicerest/createmessage' method='post'>")
				.append("Id message : <input name='idM' type='text' "
						+ readonly + " value='" + messageForm.getIdMessage()
						+ "'/><br>")
				.append("<input name='id' type='hidden' value='" + idQueue
						+ "'/>")
				.append("Objet message : <input name='objet' type='text' value='"
						+ messageForm.getObjetMessage() + "'/><br>")
				.append("Contenu message : <br><textarea name='contenu' rows='5' cols='25'>"
						+ messageForm.getContenuMessage() + "</textarea><br>")
				.append("<input type='submit' value='valider'>")
				.append(" <a href='/tpvalidation-rest/queue/servicerest/readqueue/que-"
						+ queue.getIdQueue() + "-msg-0'>annuler</a>")
				.append("</form>");
		return codeRetour.toString();
	}
}
