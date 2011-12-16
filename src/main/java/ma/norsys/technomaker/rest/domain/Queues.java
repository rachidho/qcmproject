package ma.norsys.technomaker.rest.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "queues")
public class Queues {
	private List<Queue> queues;

	
	public Queues() {
		super();
		queues =  new ArrayList<Queue>();
	}

	public List<Queue> getQueues() {
		return queues;
	}

	public void setQueues(List<Queue> queues) {
		this.queues = queues;
	}
}
