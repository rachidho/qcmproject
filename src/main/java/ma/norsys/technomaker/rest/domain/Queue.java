package ma.norsys.technomaker.rest.domain;

import java.util.LinkedList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="queue")
public class Queue {
	private String idQueue;
	private java.util.Queue<Message> lesMessages;
	
	public Queue() {
		super();
	}
	
	
	public Queue(String idQueue) {
		super();
		this.idQueue = idQueue;
		this.lesMessages = new LinkedList<Message>();
	}


	public String getIdQueue() {
		return idQueue;
	}
	public void setIdQueue(String idQueue) {
		this.idQueue = idQueue;
	}
	public java.util.Queue<Message> getLesMessages() {
		return lesMessages;
	}
	public void setLesMessages(java.util.Queue<Message> lesMessages) {
		this.lesMessages = lesMessages;
	}
	
}
