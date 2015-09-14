package com.CyborgJenn.CyBot.streamAPIs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
/**
 * Api class used to read Json from URL.
 * @author JennyLeeP
 *
 */
public class JsonHelper {
	/**
	 * Opens a buffered reader, reads the URL and closes the buffered reader.
	 * @param urlString
	 * @return
	 * @throws Exception
	 */
	public static String readJsonFromUrl(String urlString) throws Exception {
		BufferedReader reader = null;
		try {
		URL url = new URL(urlString);
		reader = new BufferedReader(new InputStreamReader(url.openStream()));
		StringBuffer buffer = new StringBuffer();
		int read;
		char[] chars = new char[1024];
		while ((read = reader.read(chars)) != -1)
		buffer.append(chars, 0, read);
		 
		return buffer.toString();
		} finally {
		if (reader != null)
		reader.close();
		}
		}
}
