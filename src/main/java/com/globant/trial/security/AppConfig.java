package com.globant.trial.security;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Jersey App config
 * 
 * @author Manish
 *
 */
public class AppConfig extends ResourceConfig{
	
    public AppConfig(){
    	packages("com.globant.trial.endpoint.impl","com.globant.trial.security");
    }
}
