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
	
	@Test(priority = 0)
	public void restApiTestWithHeaders() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		
		HashMap<String , String> headerMap = new HashMap<String , String>();
		
		headerMap.put("Content-Type" , "application/json");
		
		CloseableHttpResponse httpResponse = restClient.get(uri);
		
		int status = httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code: "+ status);
		
		Assert.assertEquals(status, status_200);
		
		String bodyResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		JSONObject jsonResponse = new JSONObject(bodyResponse);
		System.out.println("Response Json: "+ jsonResponse) ;
		
		String country = JsonOperations.readJson(jsonResponse, "/result/country");
		System.out.println("Country: "+country);
		Assert.assertEquals(country, "England");
		
		String region = JsonOperations.readJson(jsonResponse, "/result/region");
		System.out.println("Region: "+region);
		Assert.assertEquals(region, "East of England");
		
	}
	
	@AfterMethod
	public void tearDown() {
		
	}

}
