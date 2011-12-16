package ma.norsys.technomaker.rest.dao;

import java.util.List;

import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;

public interface IDaoRest {
	/**
	 * M�thode (C)RUD pour ajouter une queue
	 * 
	 * @param queue
	 * @return la queue ajout�e
	 */
	public Queue createQueue(Queue queue);

	/**
	 * M�thode C(R)UD pour lire une queue donn�e
	 * 
	 * @param idQueue
	 * @return la queue lue
	 */
	public Queue readQueue(String idQueue);

	/**
	 * M�thode pour recup�rer la liste des queues
	 * 
	 * @return liste des queues
	 */
	public List<Queue> listQueue();

	/**
	 * M�thode CRU(D) pour supprimer une queue donn�e
	 * 
	 * @param idQueue
	 * @return boolean
	 */
	public boolean deleteQueue(String idQueue);

	/**
	 * M�thode (C)RUD pour ajouter un message � une queue
	 * 
	 * @param message
	 * @param idQueue
	 * @return Message cr��
	 */
	public Message createMessage(Message message, String idQueue);

	/**
	 * M�thode C(R)UD pour lire un message d'une queue donn�e
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return Message lue
	 */
	public Message readMessage(String idMessage, String idQueue);

	/**
	 * M�thode CR(U)D pour modifier un message donn� dans une queue donn�e
	 * 
	 * @param message
	 * @param idQueue
	 * @return Message modifi�
	 */
	public Message updateMessage(Message message, String idQueue);

	/**
	 * M�thode CRU(D) pour supprimer un message donn� dans une queue donn�e
	 * 
	 * @param idMessage
	 * @param idQueue
	 * @return boolean
	 */
	public boolean deleteMessage(String idMessage, String idQueue);
}
