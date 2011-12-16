package

ma.norsys.technomaker.rest.service;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * 
 * 
 @author Norsys TechnoMaker
 */

public class QueueRestWebServiceApplication extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(ServiceRest.class);
		return classes;
	}

}