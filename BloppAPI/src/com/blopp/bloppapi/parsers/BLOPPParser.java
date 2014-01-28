package com.blopp.bloppapi.parsers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.google.gson.JsonObject;


public abstract class BLOPPParser implements ParseData
{
	protected static final String domainURL = "http://folk.ntnu.no/esbena/blopp/";
	
	private String url;
	protected InputStream inputStream;
	protected HttpGet httpGet;
	protected JsonObject json_data;
	
	public BLOPPParser(String relativeURL){
		setURL(relativeURL);
	}
	public void setURL(String relativeUrl){
		this.url = String.format("%s%s", domainURL,relativeUrl);
	}
	protected String getURL(){
		return this.url;
	}
	
	public String fetchData(){
		
		return buildJsonResult(getStreamData());
		
	}
	private InputStream getStreamData(){
		HttpClient client = HttpClientBuilder.create().build();
		httpGet = new HttpGet(getURL());
		try
		{
			HttpResponse response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			inputStream = entity.getContent();
		} catch (ClientProtocolException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return inputStream;
	} 
	private String buildJsonResult(InputStream inputStream){
		String result = null;
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"), 8);
			StringBuilder builder = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null)
			{
				builder.append(line + "\n");
			}
			inputStream.close();
			result = builder.toString();
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
}
