package com.CyborgJenn.CyBot.streamAPIs.Twitch;

import com.google.gson.JsonObject;
/**
 * <pre>
 * Class used to parse Json into objects.
 * Objects are defined by their "tag" in the Json string.
 *</pre>
 * @author JennyLeeP
 *
 */
public class Twitch_Stream {
	boolean online;
	String status;
	String game;
	String url;

/**
 * Method used to load JsonObjects from a Json String retrieved by the Twitch_Api.class.
 * @param jObject
 */
	public void load(JsonObject jObject)
	{
		setStatus(jObject.get("status").getAsString());
		setGame(jObject.get("game").getAsString());
		setUrl(jObject.get("url").getAsString());
	}

	public boolean isOnline()
	{
		return this.online; }
	public void setOnline(boolean online) { this.online = online; }

	public String getStatus() {
		return this.status; }
	public void setStatus(String status) { this.status = status; }
	
	public String getGame() {
		return this.game; }
	public void setGame(String game) { this.game = game; }
	
	public String getUrl() {
		return this.url; }
	public void setUrl(String url) { this.url = url; }
	
}
