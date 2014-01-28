package com.blopp.bloppapi.posters;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public abstract class BLOPPPoster<T> implements PostAndParseData<T> {
	
	private String urlBody = "http://folk.ntnu.no/esbena/blopp/";
	private String params;
	protected String reply;
	/**
	 * 
	 * @param params data sent in the post message. NB!! Must be "UTF-8"-encoded
	 */
	public BLOPPPoster(String phpPage, String params)
	{
		urlBody+=phpPage;
		this.params = params;
	}
	
	public void postData() throws MalformedURLException, IOException
	{
		
		reply = "";
		try
		{
			HttpURLConnection connection = setupConnection();
			
			DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream ());
			outputStream.writeBytes(params);
			outputStream.flush();
			outputStream.close();
			
			InputStream in = connection.getInputStream();
			StringBuffer sb = new StringBuffer();
			
			reply = buildString(in, sb);
			
			parseData();
			connection.disconnect();
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
		} 
	}
	private HttpURLConnection setupConnection() throws MalformedURLException, IOException{
		
		URL url;
		HttpURLConnection connection = null;
		try
		{
			url = new URL(urlBody);
		
			connection = (HttpURLConnection) url.openConnection();           
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false); 
			connection.setRequestMethod("POST"); 
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); 
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Content-Length", "" + Integer.toString(params.getBytes().length));
			connection.setUseCaches (false);
		} catch (MalformedURLException e)
		{
			e.printStackTrace();
			throw e;
			
		} catch (IOException e)
		{
			e.printStackTrace();
			throw e;
		}
		return connection;
	}
	private String buildString(InputStream in, StringBuffer sb) throws IOException
	{
		String reply;
		try {
			int chr;
			while ((chr = in.read()) != -1) {
				sb.append((char) chr);
			}
			reply = sb.toString();
		} finally {
			in.close();
		}
		return reply;
	}
	
	public String getReply()
	{
		return reply;
	}
	
}
