package ma.norsys.technomaker.rest.dao;

import java.util.List;

import ma.norsys.technomaker.rest.domain.Message;
import ma.norsys.technomaker.rest.domain.Queue;
import ma.norsys.technomaker.rest.util.SaxSingleton;

public class DaoRest implements IDaoRest {
	private SaxSingleton singleton;

	public DaoRest(String pFilePath) {
		super();
		singleton = SaxSingleton.getInstance(pFilePath);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.dao.IDaoRest#createQueue(ma.norsys.technomaker
	 * .rest.domain.Queue)
	 */
	@Override
	public Queue createQueue(Queue queue) {
		singleton.addQueueToXmlFile(queue);
		return (singleton.getQueueInXmlFile(queue.getIdQueue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ma.norsys.technomaker.rest.dao.IDaoRest#readQueue(java.lang.String)
	 */
	@Override
	public Queue readQueue(String idQueue) {
		return singleton.getQueueInXmlFile(idQueue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.dao.IDaoRest#deleteQueue(java.lang.String)
	 */
	@Override
	public boolean deleteQueue(String idQueue) {
		return singleton.deleteQueueInXmlFile(idQueue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.dao.IDaoRest#createMessage(ma.norsys.technomaker
	 * .rest.domain.Message, java.lang.String)
	 */
	@Override
	public Message createMessage(Message message, String idQueue) {
		singleton.addOrUpdateMessageInQueue(idQueue, message);
		return singleton.getMessageInQueue(idQueue, message.getIdMessage());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.dao.IDaoRest#readMessage(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Message readMessage(String idMessage, String idQueue) {
		return singleton.getMessageInQueue(idQueue, idMessage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * ma.norsys.technomaker.rest.dao.IDaoRest#updateMessage(ma.norsys.technomaker
	 * .rest.domain.Message, java.lang.String)
	 */
	@Override
	public Message updateMessage(Message message, String idQueue) {
		singleton.addOrUpdateMessageInQueue(idQueue, message);
		return singleton.getMessageInQueue(idQueue, message.getIdMessage());
	}
/*
 * (non-Javadoc)
 * @see ma.norsys.technomaker.rest.dao.IDaoRest#deleteMessage(java.lang.String, java.lang.String)
 */
	@Override
	public boolean deleteMessage(String idMessage, String idQueue) {
		return singleton.deleteMessageInQueue(idQueue, idMessage);
	}
/*
 * (non-Javadoc)
 * @see ma.norsys.technomaker.rest.dao.IDaoRest#listQueue()
 */
	@Override
	public List<Queue> listQueue() {
		return singleton.listQueueToXmlFile();
	}
}
