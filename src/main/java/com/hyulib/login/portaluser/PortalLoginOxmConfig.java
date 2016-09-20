package com.hyulib.login.portaluser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.castor.CastorMarshaller;

@Configuration
public class PortalLoginOxmConfig {
	@Bean
	public Converter getHandler(){
	  Converter handler= new Converter();
	  handler.setMarshaller(getCastorMarshaller());
	  handler.setUnmarshaller(getCastorMarshaller());
	  return handler;
	}
	@Bean
	public CastorMarshaller getCastorMarshaller() {
	  CastorMarshaller castorMarshaller = new CastorMarshaller();
	  Resource resource = new ClassPathResource("mapping.xml");
	  castorMarshaller.setMappingLocation(resource);
	  return castorMarshaller;
	}
}
