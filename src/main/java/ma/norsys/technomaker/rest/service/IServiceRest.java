package ma.norsys.technomaker.rest.service;


public interface IServiceRest {
	/**
	 * M�thode pour ajouter une queue
	 * 
	 * @param id
	 * @return String id queue ajout�e
	 */
	public String ajouterQueue(String id);

	/**
	 * M�thode pour lire une queue
	 * 
	 * @param idQueue
	 * @param idMessage
	 * @return code de retour
	 */
	public String lireQueue(String idQueue, String idMessage);

	/**
	 * M�thode pour supprimer une queue
	 * 
	 * @param idQueue
	 * @return boolean
	 */
	public String supprimerQueue(String idQueue);

	/**
	 * M�thode pour ajouter un message
	 * 
	 * @param idMessage
	 * @param objetMessage
	 * @param contenuMessage
	 * @param idQueue
	 * @return code de retour
	 */
	public String ajouterMessage(String idMessage, String objetMessage,
			String contenuMessage, String idQueue);

	/**
	 * M�thode pour lire un message
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return code de retour
	 */
	public String lireMessage(String idMessage, String idQueue);

	/**
	 * M�thode pour modifier un message
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return
	 */
	public String modifierMessage(String idMessage, String idQueue);

	/**
	 * M�thode pour supprimer un message
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return code de retour
	 */
	public String supprimerMessage(String idMessage, String idQueue);

	/**
	 * M�thode pour lister les queues
	 * 
	 * @return pr�sentation
	 */
	public String listerQueue();

}
