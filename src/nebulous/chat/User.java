package nebulous.chat;

import nebulous.networking.ClientConnection;
import nebulous.networking.ServerConnection;

public class User {
	
	private String username = "unnamed";
	private ClientConnection client;
	private ServerConnection server;

	public User(String username, ClientConnection client) {
		this.username = username;
		this.client = client;
		this.server = client.getServerConnection();
	}

	public String getUsername() {
		return username;
	}

	public ClientConnection getClient() {
		return client;
	}

	public ServerConnection getServer() {
		return server;
	}

}
