package com.n26.transaction.statistics.configuration;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import com.n26.transaction.statistics.endpoint.RestEndPoint;

@Configuration
public class JerseyConfig  extends ResourceConfig{
	
	public JerseyConfig()
	{
		register(RestEndPoint.class);
	}

}
