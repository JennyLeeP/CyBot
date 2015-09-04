package com.CyborgJenn.CyBot;

import org.jibble.pircbot.PircBot;
import org.jibble.pircbot.User;

public class CyBotOld extends PircBot{

	private boolean voiceUsers = true;

	//private String owner;
	//private IRCServer server; // this thing will contain all info on the server,
	// channels and ops in said channels.
	//private String loginPass;

	//private boolean shuttingdown = false;

	/**Version of the bot*/
	public static final String version = "C3 Java IRC Bot - V0.5.W";
	/**String that this bot will recognize as a command to it*/
	public static final String commandStart = "\\";
	
	
	private String commands = "!time, !borg, !commands";
	private String opCommands = "!op, !deop, !botleave, !joinchannel #channel, !partchannel #channel";

	public CyBotOld(String botName, String owner, String login,
			String loginPass) {
		this.setName(botName);
		//this.owner = owner;
		this.setLogin(login);
		//this.loginPass = loginPass;
		this.setVersion(version);
	}

	
	@Override
	public void onMessage(String channel, String sender, String login, String hostname, String message) {
		if (message.equalsIgnoreCase("!time")) {
			String time = new java.util.Date().toString();
			sendMessage(channel, sender + ": Thanks for caring! CyBots time is now " + time);

		}
		if (message.equalsIgnoreCase("!commands")){
			sendMessage(channel, "Valid User commands are: "+ commands );
			sendMessage(channel, "Op only commands are: "+ opCommands );
		}

		if (message.equalsIgnoreCase("!borg")){
			sendMessage(channel, "We are the Borg. Lower your shields and surrender your ships. We will add your biological and technological distinctiveness to our own. Your culture will adapt to service us. Resistance is futile.");
		}
		if (message.equalsIgnoreCase("!botleave")){
			if ((boolean) isOP(sender)){
				sendMessage(channel, "I am un-wanted and will now leave.");
				//Main.bot.quitServer();
			}
			else
			{
				sendMessage(channel, "You are not allowed to use this Command!");
			}
		}

		if (message.startsWith("!op")){
			if ((boolean) isOP(sender)){
				String userToOp = message.split(" ")[1];
				op(channel, userToOp);
				//sendMessage(channel, "/op " + userToOp);
				sendMessage(channel, "Granted Moderator Status to: " + userToOp);
			}
			else
			{
				sendMessage(channel, "You are not allowed to use this Command!");
			}

		}
		if (message.startsWith("!deop")){
			if ((boolean) isOP(sender)){
				String userToDeOp = message.split(" ")[1];
				deOp(channel, userToDeOp);
				//sendMessage(channel, "/deop " + userToDeOp);
				sendMessage(channel, "Removed Moderator Status from: " + userToDeOp);
			}
			else
			{
				sendMessage(channel, "You are not allowed to use this Command!");
			}

		}
		if (message.startsWith("!joinchannel")){
			if ((boolean) isOP(sender)){

				String channelToJoin = message.split(" ")[1];
				if (channelToJoin.startsWith("#", 0)){
					sendMessage(channel, "Now Joining channel: " + channelToJoin);
					//Main.bot.joinChannel(channelToJoin);
				}
				else
				{
					sendMessage(channel, "Channel names begin with #");
				}

			}
			else
			{
				sendMessage(channel, "You are not allowed to use this Command!");
			}
		}
		if (message.startsWith("!partchannel")){
			if ((boolean) isOP(sender)){

				String channelToPart = message.split(" ")[1];
				if (channelToPart.startsWith("#", 0)){
					sendMessage(channel, "Now leaving channel: " + channelToPart);
					//Main.bot.partChannel(channelToPart);
				}
				else
				{
					sendMessage(channel, "Channel names begin with: # ");
				}

			}
			else
			{
				sendMessage(channel, "You are not allowed to use this Command!");
			}
		}

	}

	private boolean isOP(String sender) {
		
		if (sender.equals("JennyLeeP")){
			return true;
		}else {
			return false;
		}

	}
	@SuppressWarnings("unused")
	private void addOp(){

	}

	@SuppressWarnings("unused")
	private void removeOp(){

	}

	@Override
	public void onJoin(String channel, String sender, String login, String hostname){

		if (sender.equalsIgnoreCase(getNick())){
			return;
		}else {
			sendMessage(sender,  "Hello " + sender + " Welcome to the Qubed C3 IRC Channel!");
		}

		if (voiceUsers){
			this.voice(channel, sender);
		}

	}

	@Override
	public void log(String line) {

		System.out.println(line + "\n");
	}


	public void onPrivateMessage(String sender, String login, String hostname, String message) {
		sendMessage(sender, "Bleep bleep bleep, for a good time call 1-800-THE-BORG.");
	}

	public void onUserList(String channel, User[] users) {
		for (int i = 0; i < users.length; i++) {
			User user = users[i];
			String nick = user.getNick();
			System.out.println(nick);
		}
	}

	public void onDisconnect() {
		while (!isConnected()) {
			try {
				reconnect();
			}
			catch (Exception e) {
				// Couldn’t reconnect.
				// Pause for a short while before retrying?
			}
		}
	}

	public void onKick(String channel, String kickerNick, String login, String hostname, String recipientNick, String reason) {
		if (recipientNick.equalsIgnoreCase(getNick())) {
			joinChannel(channel);
			sendMessage(channel,  "Yoy will not get rid of me that easy!");
		}
	}
}
