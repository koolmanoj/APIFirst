package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.utilities.JsonOperations;

public class TestRestApi extends TestBase{
	TestBase testBase;
	RestClient restClient;
	String url;
	String serviceUrl;
	String uri;
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		url = prop.getProperty("URL");
		serviceUrl  = prop.getProperty("ServiceURL");
		uri = url + serviceUrl;
		//System.out.println(uri);
		
	}
	
	@Test(priority = 1)
	public void restApiTest() throws ClientProtocolException, IOException {
		restClient  = new RestClient();
		CloseableHttpResponse httpResponse = restClient.get(uri);
		
		int status = httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code: "+ status);
		
		Assert.assertEquals(status, status_200);
		
		String bodyResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject jsonResponse = new JSONObject(bodyResponse);
		System.out.println("Response Json: "+ jsonResponse) ;
		
		String per_page = JsonOperations.readJson(jsonResponse, "/per_page");
		Assert.assertEquals(per_page, "6");
		
		String total = JsonOperations.readJson(jsonResponse, "/total");
		Assert.assertEquals(total, "12");
		
		String ad = JsonOperations.readJson(jsonResponse, "/ad/company");
		System.out.println("ad Object: "+ad);
		
		String id = JsonOperations.readJson(jsonResponse, "/data[0]/id");
		String first_name = JsonOperations.readJson(jsonResponse, "/data[0]/first_name");
		String last_name = JsonOperations.readJson(jsonResponse, "/data[0]/last_name");
		String email = JsonOperations.readJson(jsonResponse, "/data[0]/email");
		String avatar = JsonOperations.readJson(jsonResponse, "/data[0]/avatar");
		
		System.out.println("First Name: "+first_name);
		System.out.println("Last Name: "+last_name);
		System.out.println("Id: "+id);
		System.out.println("Email: "+email);
		System.out.println("Avatar : "+avatar);
		
		 id = JsonOperations.readJson(jsonResponse, "/data[1]/id");
		 first_name = JsonOperations.readJson(jsonResponse, "/data[1]/first_name");
		 last_name = JsonOperations.readJson(jsonResponse, "/data[1]/last_name");
		 email = JsonOperations.readJson(jsonResponse, "/data[1]/email");
		 avatar = JsonOperations.readJson(jsonResponse, "/data[1]/avatar");
		
		System.out.println("First Name: "+first_name);
		System.out.println("Last Name: "+last_name);
		System.out.println("Id: "+id);
		System.out.println("Email: "+email);
		System.out.println("Avatar : "+avatar);
		
		Header[] headerArray = httpResponse.getAllHeaders();
		HashMap<String,  String> allHeaders = new HashMap<String, String>();
		for(Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("All Headers: "+ allHeaders);
		
	}
	
	@Test(priority = 0)
	public void restApiTestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String , String> headerMap = new HashMap<String , String>();
		
		headerMap.put("Content-Type" , "application/json");
		//headerMap.put("username" , "test");
		//headerMap.put("password" , "test123");
		//headerMap.put("auth_token" , "56789");
		
		CloseableHttpResponse httpResponse = restClient.get(uri , headerMap);
		
		int status = httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code1: "+ status);
		
		Assert.assertEquals(status, status_200);
		
		String bodyResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject jsonResponse = new JSONObject(bodyResponse);
		System.out.println("Response Json: "+ jsonResponse) ;
		
		String per_page = JsonOperations.readJson(jsonResponse, "/per_page");
		Assert.assertEquals(per_page, "6");
		
		String total = JsonOperations.readJson(jsonResponse, "/total");
		Assert.assertEquals(total, "12");
		
		String ad = JsonOperations.readJson(jsonResponse, "/ad/company");
		System.out.println("ad Object: "+ad);
		
		String id = JsonOperations.readJson(jsonResponse, "/data[0]/id");
		String first_name = JsonOperations.readJson(jsonResponse, "/data[0]/first_name");
		String last_name = JsonOperations.readJson(jsonResponse, "/data[0]/last_name");
		String email = JsonOperations.readJson(jsonResponse, "/data[0]/email");
		String avatar = JsonOperations.readJson(jsonResponse, "/data[0]/avatar");
		
		System.out.println("First Name: "+first_name);
		System.out.println("Last Name: "+last_name);
		System.out.println("Id: "+id);
		System.out.println("Email: "+email);
		System.out.println("Avatar : "+avatar);
		
		 id = JsonOperations.readJson(jsonResponse, "/data[1]/id");
		 first_name = JsonOperations.readJson(jsonResponse, "/data[1]/first_name");
		 last_name = JsonOperations.readJson(jsonResponse, "/data[1]/last_name");
		 email = JsonOperations.readJson(jsonResponse, "/data[1]/email");
		 avatar = JsonOperations.readJson(jsonResponse, "/data[1]/avatar");
		
		System.out.println("First Name: "+first_name);
		System.out.println("Last Name: "+last_name);
		System.out.println("Id: "+id);
		System.out.println("Email: "+email);
		System.out.println("Avatar : "+avatar);
		
	}
	
	@AfterMethod
	public void tearDown() {
		
	}

}
