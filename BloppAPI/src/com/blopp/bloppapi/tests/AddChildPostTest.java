package com.blopp.bloppapi.tests;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Test;

import com.blopp.bloppapi.posters.AddChildPoster;

import junit.framework.TestCase;

public class AddChildPostTest extends TestCase
{
	private AddChildPoster poster;
	private static final String ENCODING_TYPE = "UTF-8";
	
	@Override
	protected void setUp() throws Exception
	{
		super.setUp();
		poster = new AddChildPoster(createDummyChild());
		poster.postData();
	}
	private String createDummyChild() throws UnsupportedEncodingException{
		try
		{
			String _states = URLEncoder.encode(Integer.toString(1), ENCODING_TYPE);
			String _name = URLEncoder.encode("Aleksander Gisvold", ENCODING_TYPE);
			return String.format("name=%s&states[]=%s", _name, _states);
		} catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	@Test
	public void test()
	{
		assertNotNull(poster.getReply());
	}
}
