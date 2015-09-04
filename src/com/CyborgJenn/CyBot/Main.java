package com.CyborgJenn.CyBot;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.JDOMException;

import com.CyborgJenn.CyBot.xml.SaveData;
import com.CyborgJenn.CyBot.xml.XMLManager;

public class Main {

	private static List<CybIrcBot> bots;
	private static Map<String, String> commands;
	private static List<String> streamersTwitch;
	private static boolean noSave = false;
	
	/**
	 * Location of the config file
	 */
	public static final String configFile = "./config.xml";
	
	

	/**
	 * Main Method for FelisBotus.
	 * Checks entered arguments, then checks for a config file. If one is found it reads it and initilizes needed bot instances.
	 * If not it gets required information from console to connect to an initial server and channel.
	 * @param args '-reset' to not use any saved configs and start new. '-nosave' to not save any changes of the bots.
	 */
	public static void main(String[] args) {
		boolean reset = false;
		for(int i = 0; i < args.length;i++){
			if (args[i].equals("-reset")) reset = true;
			if (args[i].equals("-noSave")) noSave = true;
		}
		File config = new File(configFile);
		if(reset || !config.exists()){
			if (!config.exists()) System.out.printf("No config file found in expected place\n");
			try {
				initializeNewBot();
			} catch (IOException e) {
				System.out.printf("Could not Initialize Bot.\n");
				e.printStackTrace();
			}
			commands = new HashMap<String, String>();
		}
		else {
			//initilize bots with previous file
			SaveData loadedData;
			try {
				loadedData = XMLManager.loadConfigFile();

				bots = loadedData.getBots();
				if (bots.size() == 0 ){
					System.out.printf("No saved servers found\n");
					initializeNewBot();
				}
				commands = loadedData.getCommands();
				streamersTwitch = loadedData.getTwitchStreamers();
			}
			catch (JDOMException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Enable debugging output.
		for (int i = 0; i < args.length; i++){
			if (args[i].equalsIgnoreCase("-debug")){
				for (CybIrcBot bot:bots)bot.setVerbose(true);
				break;
			}
		}

		for (CybIrcBot bot:bots){
			try {
				bot.connectConsole();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//TODO from here listen to console for specific commands

	}

	private static void initializeNewBot() throws IOException {
		
		InputStreamReader streamReader = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(streamReader);
		
		//Console console = System.console();
		System.out.println("Initilizing Bot for first time use\n\n");
	
		System.out.println("What is your IRC Name? (Not the bot's)\n");
		String owner = reader.readLine();
				
		System.out.println("What would you like this bot to be called?\n");
		String botName = reader.readLine();
				
		System.out.println("What is the login email address for the bot?\n");
		String login = reader.readLine();
		
		System.out.println("Would you like to save a password for authentication? Y/N\n(Not Advised; this will be stored in plaintext)\n");
		String response = reader.readLine();
		
		
		String loginPass = null;
		if (response.startsWith("y") || response.startsWith("Y")){//could probably be smart about this but eh, only expect y/n or yes/no
			System.out.println("Please enter a password");
			
			loginPass = reader.readLine().toString();
		}
		bots = new ArrayList<CybIrcBot>();
		bots.add(new CybIrcBot(botName, owner, login, loginPass));//initilize new bot
	}

	/**
	 * Method to save all information about the current running bot. As each instance can only connect to one server it is managed from here.
	 * @return Returns true if save successful and false otherwise.
	 * @throws IOException
	 */
	public static boolean save() throws IOException{
		if (noSave) return false;
		XMLManager.compileConfigFile(bots, commands, streamersTwitch);
		return true;
	}

	//TODO Method to reload all bots?

	/**
	 * Method to get the response for the entered command.
	 * @param command Command to find a response for
	 * @return Response to command or null if command does not exist in Map
	 */
	public static String getResponse(String command){
		return commands.get(command);
	}

	/**
	 * Stores a command and it's response into the map
	 * @param command Command to be used by others
	 * @param response Response that bot sends out to others
	 * @return old response if command already exists, null otherwise.
	 */
	public static String putCommand(String command, String response){
		return commands.put(command, response);
	}
	
	public static String removeCommand(String command){
		return commands.remove(command);
	}

	public static void removeBot(CybIrcBot bot) {
		bot.shutDown();
		bots.remove(bot);
		try {
			save();
		} catch (IOException e) {
			System.console().printf("\nError saving config file\n");
			e.printStackTrace();
		}
	}

	public static CybIrcBot getBotConnectedTo(String string) {
		for (CybIrcBot currBot:bots){
			if (currBot.getServer().equalsIgnoreCase(string)){
				return currBot;
			}
		}
		return null;
		
	}
	
	public static void shutItDown(boolean force) throws IOException{
		try {
			Main.save();
		} catch (IOException e) {
			if (!force)throw e;
		}
		for (CybIrcBot bot:bots){
			bot.shutDown();
		}
		System.exit(0);
		
	}

	//TODO create a new bot on command
	//TODO remove a bot on command
}
