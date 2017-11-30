package com.olib.maker.core;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;

public class OlibMaker {

	private final String MODULES_KEY="projectModules";
	private final String PROJECT_NAME="projectName";
	
	private static OlibMaker olibMaker;
	
	private String regex;
	private Pattern pattern;
	
	public OlibMaker(){
		this.initializePattern();
	}

	public static OlibMaker getInstance(){
		if(olibMaker == null){
			olibMaker = new OlibMaker();
		}
		return olibMaker;
	}
	
	public void run(
			String templatePath, 
			String distPath, 
			Map<String,String> propertiesMap){
		
		File templateDir = new File(templatePath);
		File distDir = new File(distPath);
		if(!distDir.exists()){
			distDir.mkdir();
		}
		VelocityEngine velocity = initializeVelocity(templateDir);
		
		writeDirectory(velocity,templateDir, distDir, propertiesMap);
	}
	
	private void initializePattern(){
		String fieldStart = "\\$\\{";
		String fieldEnd = "\\}";
		this.regex = fieldStart + "([^}]+)" + fieldEnd;
		this.pattern = Pattern.compile(regex);
	}
	
	private void writeModulesDirectory(
			File distDir, 
			Map<String, String> propertieMap){
		
		if(propertieMap.containsKey(MODULES_KEY)){
			String[] moduleNames = propertieMap.get(MODULES_KEY).split(",");
			String projectName = propertieMap.get(PROJECT_NAME);
			
			for(String moduleName : moduleNames){
				File destinationDirectory = new File(distDir,String.format("%s-%s",projectName,moduleName).toString());
				destinationDirectory.mkdirs();
			}
		}
		
	}
	
	private void writeDirectory(
			VelocityEngine velocity, 
			File templateDir, 
			File distDir, 
			Map<String, String> propertieMap){
		
		File[] sourceFiles = templateDir.listFiles();
		
		for(File checkedFile:sourceFiles){
			if(checkedFile.isDirectory()){
				String directoryNameFormat = getFilterdString(checkedFile.getName(),propertieMap);
				File destinationDirectory = new File(distDir,directoryNameFormat);
				destinationDirectory.mkdirs();
				
				writeDirectory(velocity, checkedFile, destinationDirectory, propertieMap);
				
				if(directoryNameFormat.indexOf("-modules") >= 0){
					writeModulesDirectory(destinationDirectory, propertieMap);
				}
				
				System.out.println(String.format("[CREATE][ %s ]", destinationDirectory.getAbsolutePath()));
			}else{
				String filterdFileName = getFilterdString(checkedFile.getName(), propertieMap);
				String templatePath = checkedFile.getAbsolutePath().replace(velocity.getProperty("file.resource.loader.path")+"", "");
				String distPath = distDir.getAbsolutePath()+"/"+filterdFileName;
				
				writeFile(velocity, templatePath, distPath, propertieMap);
				System.out.println(String.format("[CREATE][ %s/%s ]",distDir.getAbsolutePath(), filterdFileName));
			}
		}
	}
	
	private void writeFile(
			VelocityEngine velocity, 
			String sourcePath,
			String destinationPath,
			Map<String, String> propertieMap){
		
		FileWriter fileWriter = null;
		
		try{
			fileWriter = new FileWriter(destinationPath);
			Template template = velocity.getTemplate(sourcePath, "UTF-8");
			template.merge(new VelocityContext(propertieMap), fileWriter);
			fileWriter.flush();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				fileWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
	
	private VelocityEngine initializeVelocity(File templateDir){
		VelocityEngine result = new VelocityEngine();		
		result.setProperty(RuntimeConstants.RESOURCE_LOADER,"file");
		result.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH,templateDir.getAbsolutePath());
		result.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_CACHE, "true");
		result.init();
		return result;
	}
	
	private String getFilterdString(
			String targetString, 
			Map<String, String> propertieMap) {
		Matcher matcher = pattern.matcher(targetString);
		String result = targetString;
		
		while (matcher.find()) {
			String found = matcher.group(1);
			String newVal = propertieMap.get(found);
			if(newVal != null){
				result = result.replaceFirst(regex, newVal);
			}
		}
		return result;
	}
}
