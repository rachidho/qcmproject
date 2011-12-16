package ma.norsys.technomaker.rest.dao;

import java.util.List;

import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;

public interface IDaoRest {
	/**
	 * Méthode (C)RUD pour ajouter une queue
	 * 
	 * @param queue
	 * @return la queue ajoutée
	 */
	public Queue createQueue(Queue queue);

	/**
	 * Méthode C(R)UD pour lire une queue donnée
	 * 
	 * @param idQueue
	 * @return la queue lue
	 */
	public Queue readQueue(String idQueue);

	/**
	 * Méthode pour recupérer la liste des queues
	 * 
	 * @return liste des queues
	 */
	public List<Queue> listQueue();

	/**
	 * Méthode CRU(D) pour supprimer une queue donnée
	 * 
	 * @param idQueue
	 * @return boolean
	 */
	public boolean deleteQueue(String idQueue);

	/**
	 * Méthode (C)RUD pour ajouter un message à une queue
	 * 
	 * @param message
	 * @param idQueue
	 * @return Message créé
	 */
	public Message createMessage(Message message, String idQueue);

	/**
	 * Méthode C(R)UD pour lire un message d'une queue donnée
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return Message lue
	 */
	public Message readMessage(String idMessage, String idQueue);

	/**
	 * Méthode CR(U)D pour modifier un message donné dans une queue donnée
	 * 
	 * @param message
	 * @param idQueue
	 * @return Message modifié
	 */
	public Message updateMessage(Message message, String idQueue);

	/**
	 * Méthode CRU(D) pour supprimer un message donné dans une queue donnée
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return boolean
	 */
	public boolean deleteMessage(String idMessage, String idQueue);
}
