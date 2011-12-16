package ma.norsys.technomaker.rest.service;


public interface IServiceRest {
	/**
	 * Méthode pour ajouter une queue
	 * 
	 * @param id
	 * @return String id queue ajoutée
	 */
	public String ajouterQueue(String id);

	/**
	 * Méthode pour lire une queue
	 * 
	 * @param idQueue
	 * @param idMessage
	 * @return code de retour
	 */
	public String lireQueue(String idQueue, String idMessage);

	/**
	 * Méthode pour supprimer une queue
	 * 
	 * @param idQueue
	 * @return boolean
	 */
	public String supprimerQueue(String idQueue);

	/**
	 * Méthode pour ajouter un message
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
	 * Méthode pour lire un message
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return code de retour
	 */
	public String lireMessage(String idMessage, String idQueue);

	/**
	 * Méthode pour modifier un message
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return
	 */
	public String modifierMessage(String idMessage, String idQueue);

	/**
	 * Méthode pour supprimer un message
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return code de retour
	 */
	public String supprimerMessage(String idMessage, String idQueue);

	/**
	 * Méthode pour lister les queues
	 * 
	 * @return présentation
	 */
	public String listerQueue();

}
