package com.qa.test;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.ParseException;
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
	String uri1;
	String uri2;
	String ServiceURLnearest;
	String ServiceURLvalidate;
	
	@BeforeMethod
	public void setUp() {
		testBase = new TestBase();
		url = prop.getProperty("URL");
		serviceUrl  = prop.getProperty("ServiceURL");
		ServiceURLnearest = prop.getProperty("ServiceURLnearest");
		ServiceURLvalidate = prop.getProperty("ServiceURLvalidate");
		uri = url + serviceUrl;
		uri1 = url + ServiceURLnearest;
		uri2 = url + ServiceURLvalidate;
		//System.out.println(uri);
		
	}
	
	@Test(priority = 1)
	public void checkPostcode() {
		restClient = new RestClient();
		
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = restClient.get(uri);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int status = httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code: "+ status);
		
		Assert.assertEquals(status, status_200, "Error: Request Invalid Please check");
		
		
		if(status == 200) {
		String bodyResponse = null;
		try {
			bodyResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonResponse = new JSONObject(bodyResponse);
		System.out.println("Response Json: "+ jsonResponse) ;
		
		String country = JsonOperations.readJson(jsonResponse, "/result/country");
		System.out.println("Country: "+country);
		Assert.assertEquals(country, "England");
		
		String region = JsonOperations.readJson(jsonResponse, "/result/region");
		System.out.println("Region: "+region);
		Assert.assertEquals(region, "East of England");
		}
		
	}
	
	@Test(priority = 2)
	public void nearestPostcode() {
		
		restClient = new RestClient();
		
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = restClient.get(uri1);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int status = httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code: "+ status);
		
		Assert.assertEquals(status, status_200, "Error: Request Invalid Please check");
		
		
		if(status == 200) {
		String bodyResponse = null;
		try {
			bodyResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonResponse = new JSONObject(bodyResponse);
		System.out.println("Response Json: "+ jsonResponse) ;
		
		String country = JsonOperations.readJson(jsonResponse, "/result/country");
		System.out.println("Country: "+country);
		Assert.assertEquals(country, "England");
		
		String region = JsonOperations.readJson(jsonResponse, "/result/region");
		System.out.println("Region: "+region);
		Assert.assertEquals(region, "East of England");
		}
	}
	
	@Test(priority = 0)
	public void validatePostcode() {
		restClient = new RestClient();
		
		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = restClient.get(uri2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int status = httpResponse.getStatusLine().getStatusCode();
		System.out.println("status code: "+ status);
		Assert.assertEquals(status, status_200, "Error: Request Invalid Please check");
		
		String bodyResponse = null;
		try {
			bodyResponse = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jsonResponse = new JSONObject(bodyResponse);
		System.out.println("Response Json: "+ jsonResponse) ;
		
		String result = JsonOperations.readJson(jsonResponse, "/result");
		System.out.println("Result: "+result);
		Assert.assertEquals(result, "true", "Result notg as expected please check");
		
	}
	
	@AfterMethod
	public void tearDown() {
		
	}

}
