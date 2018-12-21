package org.upec.masters.app;


import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.upec.andric.test.*;

@ApplicationPath("/")
public class Application extends ResourceConfig {

	public Application() {
		// Register resources and providers using package-scanning.
		packages("org.upec.andric.test");

		// Register my custom provider - not needed if it's in my.package.
		register(MyRessources.class);
		// Register an instance of LoggingFilter.
		

	}
	
	
}
