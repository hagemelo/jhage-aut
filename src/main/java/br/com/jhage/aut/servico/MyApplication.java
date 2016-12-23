package br.com.jhage.aut.servico;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;


public class MyApplication extends ResourceConfig{
	
	public MyApplication(){
		register(new ApplicationBinder());
		register(RequestContextFilter.class);
		packages("br.com.jhage.aut.servico");
	}

}
