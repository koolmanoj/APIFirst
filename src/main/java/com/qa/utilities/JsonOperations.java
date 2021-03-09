package com.qa.utilities;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonOperations {
	
	public static String readJson(JSONObject jsonResponse, String jPath) {
		
		Object obj = jsonResponse;
		for(String s : jPath.split("/")) {
			//.out.println("JPath Spilt> "+s);
			
			if(!s.isEmpty()) {
				if(!(s.contains("[") || s.contains("]"))) {
					obj = ((JSONObject) obj).get(s);
				}
				
				else if(s.contains("[") || s.contains("]")) {
					obj = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0])).get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
					
				}
			}
			
		}
		
		return obj.toString();
	}

}
