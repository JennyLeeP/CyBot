package com.CyborgJenn.CyBot.streamAPIs.HitBox;

import com.CyborgJenn.CyBot.streamAPIs.JsonHelper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;


public class HitBox_API {

	public static Gson gson = new Gson();

	public static HitBox_Stream getStream(String channelName){
		try {
			String userJsonArray = JsonHelper.readJsonFromUrl("http://api.hitbox.tv/user/"+channelName);
			String channelInfo = JsonHelper.readJsonFromUrl("http://api.hitbox.tv/media/live/"+channelName);
			JsonObject userJsonObject = gson.fromJson(userJsonArray, JsonObject.class);
			JsonObject channelJsonObject = gson.fromJson(channelInfo, JsonObject.class);
			HitBox_Stream stream = new HitBox_Stream();
			
			if (userJsonObject.getAsJsonPrimitive("is_live").getAsInt() != 0){
				stream.setOnline(true);
				stream.load(channelJsonObject);	
			}
			else
			{
				stream.setOnline(false);
			}
			return stream;
		} catch (Exception e) {
			System.out.println(e+"Caused by getStream in HitBox_Api");
			e.printStackTrace();
		}
		return null;
	}
}


