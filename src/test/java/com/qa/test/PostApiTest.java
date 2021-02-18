package com.qa.test;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.data.Users;

public class PostApiTest extends TestBase {

	TestBase testBase;
	RestClient restClient;
	String url;
	String serviceUrl;
	String uri;

	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		url = prop.getProperty("URL");
		serviceUrl = prop.getProperty("ServiceURL");
		uri = url + serviceUrl;
		// System.out.println(uri);

	}

	@Test
	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {

		restClient = new RestClient();

		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Content-Type", "application/json");
		// headerMap.put("username" , "test");
		// headerMap.put("password" , "test123");
		// headerMap.put("auth_token" , "56789");

		// jackson API
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader");

		// object to json (Marshelling)
		mapper.writeValue(
				new File("G:\\Development\\Projects\\RestApiAutomation\\src\\main\\java\\com\\qa\\data\\users.json"),
				users);
		String userJsonString = mapper.writeValueAsString(users);

		System.out.println(userJsonString);

		CloseableHttpResponse httpResponse = restClient.post(uri, userJsonString, headerMap);
		 
		 int status = httpResponse.getStatusLine().getStatusCode();
		 System.out.println("status code: "+ status);
		 Assert.assertEquals(status , TestBase.status_201);
		 
		 // json to Object (UnMarshaling)
		 String jsonResponseString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		 
		 JSONObject jsonResponse = new JSONObject(jsonResponseString);
		 System.out.println("Response from API: "+ jsonResponse);
		 
		 Users userResponseObj = mapper.readValue(jsonResponseString , Users.class);
		 System.out.println(userResponseObj);
		 
		 System.out.println("Id: "+userResponseObj.getId());
		 System.out.println("CreatedAt: "+userResponseObj.getCreatedAt());
		 System.out.println("Name: "+userResponseObj.getName());
		 System.out.println("Job: "+userResponseObj.getJob());
		 
		 Assert.assertEquals(userResponseObj.getName(), users.getName());
		 Assert.assertEquals(userResponseObj.getJob(), users.getJob());
		 
	}

}
