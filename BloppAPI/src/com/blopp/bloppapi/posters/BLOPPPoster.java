package com.blopp.bloppapi.posters;

import org.apache.http.client.methods.HttpPost;

public abstract class BLOPPPoster implements PostAndParseData {
	
	private HttpPost httpPost;
	private String urlBody = "http://folk.ntnu.no/esbena/blopp";
	private String params;
	/**
	 * 
	 * @param params data sent in the post message. NB!! Must be "UTF-8"-encoded
	 */
	public BLOPPPoster(String params, String phpPage)
	{
		urlBody+=phpPage;
		this.params = params;
	}
	
	
}
