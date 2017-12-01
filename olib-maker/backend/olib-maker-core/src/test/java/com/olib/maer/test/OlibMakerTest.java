package com.olib.maer.test;

import java.util.HashMap;
import java.util.Map;

import com.olib.maker.core.OlibMaker;
import com.olib.maker.util.OlibZipUtil;

public class OlibMakerTest {
	
	public static void olibTemplateTest(){
		OlibMaker maker = OlibMaker.getInstance();
		String templatePath="/templatePath";
		String distPath="/distPath";
		
		Map<String, String> dataMap = new HashMap<>();
		dataMap.put("projectSpringBootVersion", "1.5.7.RELEASE");
		dataMap.put("projectName", "olib-project");
		dataMap.put("projectClass", "OlibProject");
		dataMap.put("projectRootArtifactId", "olib-project");
		dataMap.put("projectGroup", "com.olib");
		dataMap.put("projectPath", "com/olib/");
		
		maker.run(templatePath, distPath, dataMap);
		
	}
	
	public static void main(String[] ar){
		olibTemplateTest();
	}
}
