package com.blopp.bloppasthma.div;

import java.io.IOException;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.util.Log;
//Do not fucking touch me
public class SoundStreamer
{
	private String url;
	private OnCompletionListener completionListener;
	private MediaPlayer mediaPlayer;
	private static SoundStreamer instance;
	
	
	private SoundStreamer(String url, OnCompletionListener completionListener)
	{
		this.url = url;
		this.completionListener = completionListener;
	}
	public static SoundStreamer getInstance(String url, OnCompletionListener completionListener){
		if(instance == null)
		{
			instance = new SoundStreamer(url, completionListener);
		}else{
			setUrl(url);
			setCompletionListener(completionListener);
		}
		return instance;
	}
	private static SoundStreamer setUrl(String url){
		instance.url = url;
		return instance;
	}
	private static SoundStreamer setCompletionListener(OnCompletionListener listener)
	{
		instance.completionListener = listener;
		return instance;
	}
	public MediaPlayer getMediaPlayerInstance(){
		if(instance.mediaPlayer != null)
		{
			instance.mediaPlayer.reset();
			return mediaPlayer;
		}else{
			instance.mediaPlayer = new MediaPlayer();
			return mediaPlayer; 
		}
	}
	public SoundStreamer(String url)
	{
		this.url = url;
	}
	
	
	public void play()
	{
		mediaPlayer = getMediaPlayerInstance();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		
		try
		{
			mediaPlayer.setDataSource(url);
		
		} catch (IllegalArgumentException e)
		{
//			Log.e("setAudioStreamType", e.getMessage());
			e.printStackTrace();
		} catch (SecurityException e)
		{
//			Log.e("setAudioStreamType", e.getMessage());
			e.printStackTrace();
		} catch (IllegalStateException e)
		{
//			Log.e("setAudioStreamType", e.getMessage());
			e.printStackTrace();
		} catch (IOException e)
		{
			e.printStackTrace();
//			Log.e("setAudioStreamType", e.getMessage());
		}
		
		mediaPlayer.prepareAsync();
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			public void onPrepared(MediaPlayer mp)
			{
				mp.start();
			}
		});
		
		mediaPlayer.setOnCompletionListener(new OnCompletionListener()
		{
			public void onCompletion(MediaPlayer mp)
			{
				mp.release();
			}
		});
		
		if (completionListener != null)
		{
			mediaPlayer.setOnCompletionListener(completionListener);
		}
		
	}

}
