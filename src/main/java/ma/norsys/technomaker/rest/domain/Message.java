package ma.norsys.technomaker.rest.domain;

public class Message {
	private String idMessage;
	private String objetMessage;
	private String contenuMessage;

	public Message() {
		super();
	}

	public Message(String idMessage, String objetMessage, String contenuMessage) {
		super();
		this.idMessage = idMessage;
		this.objetMessage = objetMessage;
		this.contenuMessage = contenuMessage;
	}

	public String getIdMessage() {
		return idMessage;
	}

	public void setIdMessage(String idMessage) {
		this.idMessage = idMessage;
	}

	public String getObjetMessage() {
		return objetMessage;
	}

	public void setObjetMessage(String objetMessage) {
		this.objetMessage = objetMessage;
	}

	public String getContenuMessage() {
		return contenuMessage;
	}

	public void setContenuMessage(String contenuMessage) {
		this.contenuMessage = contenuMessage;
	}

}
