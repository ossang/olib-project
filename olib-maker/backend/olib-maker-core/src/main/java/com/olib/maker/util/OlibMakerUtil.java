package com.olib.maker.util;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class OlibMakerUtil {

	public static Map<String,String> jsonToMap(String json){
		HashMap<String, String> result = null;
		
		try {
			result = new ObjectMapper().readValue(json,HashMap.class);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	
		return result;
	}
}
