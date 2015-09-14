package com.CyborgJenn.CyBot.streamAPIs.Beam;

import com.google.gson.JsonObject;

public class Beam_Stream {
	
boolean online;
String username;
String title;
String game;
String url;

public void load(JsonObject jObject) {
	
	setUser(jObject.getAsJsonPrimitive("token").getAsString());
	setTitle(jObject.getAsJsonPrimitive("name").getAsString());
	setGame(jObject.getAsJsonObject("type").getAsJsonPrimitive("name").getAsString());
	setUrl(String.format("https://beam.pro/"+jObject.getAsJsonPrimitive("token").getAsString()));
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
