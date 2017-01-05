package com.xavient.test.script;

import static com.jayway.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.response.Response;

public class Test1_API {
	static Logger logger = LogManager.getLogger(Test1_API.class);

	public static void main(String[] args) throws IOException {
		int responseStatus = getAPIResponseCode(
				"C:\\Users\\nkumar9\\Desktop\\view1.json",
				"http://10.5.3.245/MercuryServices/rest/mercuryview/getData");
		logger.info("API respone code:" + responseStatus);
		String responseBody = getAPIResponse(
				"C:\\Users\\nkumar9\\Desktop\\view1.json",
				"http://10.5.3.245/MercuryServices/rest/mercuryview/getData");
		JSONObject json = null;
		JSONArray nameArray = new JSONArray();
		try {
			json = new JSONObject(responseBody);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		try {
			nameArray = json.getJSONArray("data");
			logger.info("Array size is :"+nameArray.length());
			//for (int i = 0; i < nameArray.length(); i++) {
				JSONObject innerData = nameArray.getJSONObject(nameArray.length()-1);
				logger.info("JSON Data:"+innerData);
				Map<String, Object> map = jsonToMap(innerData);
				logger.info("json Array"+(nameArray.length()-1)+ " :" + map);
				Set<String> keys =map.keySet();
				logger.info("Keys are :"+keys);
				Object value = map.get("child");
				logger.info("Value is :"+value);
				 if(value instanceof JSONArray) {
			            value = toList((JSONArray) value);
			        }

			        else if(value instanceof JSONObject) {
			            value = toMap((JSONObject) value);
			        }
				
			/*for (int i = 0; i < nameArray.length(); i++) {

				JSONObject innerData = nameArray.getJSONObject(i);
				String viewTemplate = innerData.getString("viewTemplate");
				System.out.println("View template data" + i + " is :"
						+ viewTemplate);
				JSONArray childData = innerData.getJSONArray("child");
				for (int j = 0; j < childData.length(); j++) {
					JSONObject innerData2 = childData.getJSONObject(i);
					String innerViewTeplate = innerData2
							.getString("viewTemplate");
					System.out.println(" Inner View template data" + j
							+ " is :" + innerViewTeplate);
					JSONArray innerChildData = innerData2.getJSONArray("child");
					for (int k = 0; k < innerChildData.length(); k++) {
						JSONObject innerData3 = innerChildData.getJSONObject(k);
						String intermViewTeplate = innerData3.getString("viewTemplate");
						System.out.println(" Interrm View template data" + k
								+ " is :" + intermViewTeplate);
					}
				}

			}*/

		} catch (JSONException e) {

			e.printStackTrace();
		}

		

	}

	public static String getAPIResponse(String path, String URL) {
		String jsonBody = generateStringFromResource(path);
		

		Response response =(Response)given().contentType("application/json")
				.body(jsonBody).when().post(URL);

		String responseBody = response.getBody().asString();
		return responseBody;

	}

	private static String generateStringFromResource(String path) {
		try {
			return new String(Files.readAllBytes(Paths.get(path)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static int getAPIResponseCode(String filepath, String URL) {
		String jsonBody = generateStringFromResource(filepath);
		Response response = given().body(jsonBody)
				.contentType("application/json").when().post(URL);
		return response.getStatusCode();

	}
	
	
	public static Map<String, Object> jsonToMap(JSONObject json) throws JSONException {
	    Map<String, Object> retMap = new HashMap<String, Object>();

	    if(json != JSONObject.NULL) {
	        retMap = toMap(json);
	    }
	    return retMap;
	}

	public static Map<String, Object> toMap(JSONObject object) throws JSONException {
	    Map<String, Object> map = new HashMap<String, Object>();

	    @SuppressWarnings("unchecked")
		Iterator<String> keysItr = object.keys();
	    while(keysItr.hasNext()) {
	        String key = keysItr.next();
	        Object value = object.get(key);

	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        map.put(key, value);
	    }
	    return map;
	}

	public static List<Object> toList(JSONArray array) throws JSONException {
	    List<Object> list = new ArrayList<Object>();
	    for(int i = 0; i < array.length(); i++) {
	        Object value = array.get(i);
	        if(value instanceof JSONArray) {
	            value = toList((JSONArray) value);
	        }

	        else if(value instanceof JSONObject) {
	            value = toMap((JSONObject) value);
	        }
	        list.add(value);
	    }
	    return list;
	}

}
