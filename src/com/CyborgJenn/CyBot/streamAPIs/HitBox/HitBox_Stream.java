package com.CyborgJenn.CyBot.streamAPIs.HitBox;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class HitBox_Stream {

	String username;
	Boolean online;
	String title;
	String game;
	String url;
	
	/**
	 * Method used to load JsonObjects from a Json String retrieved by the HitBox_Api.class.
	 * @param jObject
	 */
	public void load(JsonObject jObject)
	{
		JsonArray livestream = jObject.getAsJsonArray("livestream");
		JsonElement media = livestream.get(0);
		setUser(media.getAsJsonObject().get("media_user_name").getAsString());
		setGame(media.getAsJsonObject().get("category_seo_key").getAsString());
		setTitle(media.getAsJsonObject().get("media_status").getAsString());
		setUrl(String.format("http://hitbox.tv/"+media.getAsJsonObject().get("media_user_name").getAsString()));	
	}
	public boolean getIsOnline(){return this.online; }
	public void setOnline(boolean online) { this.online = online; }
	
	public String getUser() { return this.username; }
	public void setUser(String username) { this.username = username; }

	public String getTitle() { return this.title; }
	public void setTitle(String title) { this.title = title; }
	
	public String getGame() { return this.game; }
	public void setGame(String game) { this.game = game; }
	
	public String getUrl() { return this.url; }
	public void setUrl(String url) { this.url = url; }
}
