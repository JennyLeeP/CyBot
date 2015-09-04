package com.CyborgJenn.CyBot.irc;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class to contain information about an IRC server
 * @author Reece
 *
 */
public class IRCServer {

	private Map<String, IRCChannel> channels = new HashMap<String, IRCChannel>();
	private String serverAddress;


	/**
	 * Create an instance using a server address and set of saved channels
	 * @param serverAddress Address used to connect to the server
	 * @param channels
	 */
	public IRCServer(String serverAddress,
			Set<IRCChannel> channels) {
		super();
		this.serverAddress = serverAddress;
		for (IRCChannel currChannel:channels){
			this.channels.put(currChannel.getName(), currChannel);
		}
	}

	/**
	 * Create an instance using just a server address
	 * @param serverAddress
	 */
	public IRCServer(String serverAddress) {
		super();
		this.serverAddress = serverAddress;
		this.channels = new HashMap<String,IRCChannel>();
	}

	/**
	 * Get the set of saved channels for this server
	 * @return Set of saved channels
	 */
	public Collection<IRCChannel> getChannels() {
		return Collections.unmodifiableCollection(channels.values());
	}

	/**
	 * Get the IRCChannel object for the channel with channelNAme
	 * @param channelName Name of channel to get object for
	 * @return IRCChannel object
	 */
	public IRCChannel getChannel(String channelName){
		return channels.get(channelName);
	}

	/**
	 * Get the server address
	 * @return Server address
	 */
	public String getServerAddress() {
		return serverAddress;
	}

	/**
	 * Add a channel to the set of saved channels
	 * @param newChannel Channel to save
	 * @return Old channel if one is already saved, null otherwise.
	 */
	public IRCChannel addChannel(IRCChannel newChannel){
		return channels.put(newChannel.getName(), newChannel);
	}

	/**
	 * Remove a channel from the saved set
	 * @param oldChannel Channel to remove
	 * @return IRCChannel that was removed
	 */
	public IRCChannel removeChannel(String oldChannel){
		return channels.remove(oldChannel);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((channels == null) ? 0 : channels.hashCode());
		result = prime * result
				+ ((serverAddress == null) ? 0 : serverAddress.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof IRCServer))
			return false;
		IRCServer other = (IRCServer) obj;
		if (channels == null) {
			if (other.channels != null)
				return false;
		} else if (!channels.equals(other.channels))
			return false;
		if (serverAddress == null) {
			if (other.serverAddress != null)
				return false;
		} else if (!serverAddress.equals(other.serverAddress))
			return false;
		return true;
	}

}
