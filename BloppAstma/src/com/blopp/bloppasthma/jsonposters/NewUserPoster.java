package com.blopp.bloppasthma.jsonposters;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import android.os.AsyncTask;
import android.util.Log;

public class NewUserPoster extends AsyncTask<Void, Void, String>
{
	private static final String TAG = NewUserPoster.class.getSimpleName();
	private HttpGet httpGet;
	private String urlBody = "http://129.241.103.246:1337/newChild?childId=";
	
	public NewUserPoster(String childId){
		urlBody += childId;
	}

	@Override
	protected String doInBackground(Void... params)
	{
		URL url;
		String reply;
		BufferedReader br;
		String line;
		String result = "";
		try{
			url = new URL(urlBody);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while((line = br.readLine()) != null){
				result+=line;
			}
			br.close();
		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		Log.d(TAG, result);
		return result;
	}

	
}
