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

public class NewUserPoster extends AsyncTask<Void, Void, Boolean>
{
	private static final String TAG = NewUserPoster.class.getSimpleName();
	private HttpGet httpGet;
	private String urlStarter = "http://"
			, page ="/newChild?childId=";
	private String urlBody = "";
	
	public NewUserPoster(String ip, String childId){
		urlBody += urlStarter + ip + page + childId;
	}

	@Override
	protected Boolean doInBackground(Void... params) 
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
			return false;
		}catch(IOException e)
		{
			return false;
		}
		Log.d(TAG, result);
		return true;
	}
	
}
