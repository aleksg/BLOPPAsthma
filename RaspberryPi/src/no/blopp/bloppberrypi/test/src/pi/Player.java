package pi;

import java.io.BufferedInputStream;
import java.io.FileInputStream;

import javazoom.jl.decoder.JavaLayerException;

public class Player {

	public static void main(String[] args) 
	{       
	    Player player = null;

	    try 
	    {
	        FileInputStream fis = new FileInputStream("song.mp3");
	        BufferedInputStream bis = new BufferedInputStream(fis);
	        player = new Player(bis);
	    } 
	    catch (Exception e)
	    {
	        System.out.println(e.getMessage());
	    }

	    try 
	    {
	        player.play();
	    } 
	    catch (JavaLayerException e) 
	    {
	        System.out.println(e.getMessage());
	    }   
	}

}
