package com.blopp.bloppapi.posters;

import java.io.IOException;
import java.net.MalformedURLException;

public interface PostAndParseData {
	
	void postData() throws MalformedURLException, IOException;
	abstract void parseData();

}
