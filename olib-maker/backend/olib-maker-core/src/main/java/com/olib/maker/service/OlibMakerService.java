package com.olib.maker.service;

import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

import com.olib.maker.core.OlibMaker;

@Service
public class OlibMakerService implements InitializingBean{

	private OlibMaker maker;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.maker = OlibMaker.getInstance();
	}
	
	public void run(
			String templatePath, 
			String distPath, 
			Map<String, String> propertiesMap){
		
		maker.run(templatePath, distPath, propertiesMap);
	}
	
	
	

}
