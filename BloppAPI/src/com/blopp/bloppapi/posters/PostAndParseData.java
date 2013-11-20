package com.blopp.bloppapi.posters;

import java.io.IOException;
import java.net.MalformedURLException;

public interface PostAndParseData<T> {
	
	void postData() throws MalformedURLException, IOException;
	abstract <T> T parseData();

}
