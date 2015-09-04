package com.CyborgJenn.CyBot.xml;

import java.util.List;
import java.util.Map;

import com.CyborgJenn.CyBot.CybIrcBot;

/**
 * Class for storing data returned from reading the XML config file
 * @author Reece
 *
 */
public class SaveData {

	private List<CybIrcBot> bots;
	private Map<String, String> Commands;
	private List<String> streamersTwitch;

	/**
	 * Create an instance of save data supplying the list of pots and map of commands
	 * @param bots List of bots created
	 * @param commands Map of commands
	 */
	public SaveData(List<CybIrcBot> bots, Map<String, String> commands, List<String> streamersTwitch) {
		super();
		this.bots = bots;
		Commands = commands;
		this.streamersTwitch = streamersTwitch;
	}

	/**
	 * Get the list of bots
	 * @return List of bots
	 */
	public List<CybIrcBot> getBots() {
		return bots;
	}

	/**
	 * Get the map of commands
	 * @return Map of commands
	 */
	public Map<String, String> getCommands() {
		return Commands;
	}

	public List<String> getTwitchStreamers(){
		return streamersTwitch;
	}
}
