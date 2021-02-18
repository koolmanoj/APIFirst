package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.qa.base.TestBase;

public class RestClient extends TestBase{
	
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		
		return httpResponse;
	}
	
	public CloseableHttpResponse get(String url, HashMap<String , String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			
			httpGet.addHeader(entry.getKey() , entry.getValue());
			
		}
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
		
		return httpResponse;
	}
	
	public CloseableHttpResponse post(String url, String entityString, HashMap<String , String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		
		httpPost.setEntity(new StringEntity(entityString));
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()) {
			
			httpPost.addHeader(entry.getKey() , entry.getValue());
			
		}
		
		CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
		
		return httpResponse;
	}
	

}
