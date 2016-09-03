package nebulous.networking;

public class ServerConnection {
	
	private String remoteIP = "0.0.0.0";
	private short remotePort = 0;

	public ServerConnection(String ip, short port) {
		this.remoteIP = ip;
		this.remotePort = port;
	}

	public String getRemoteIP() {
		return remoteIP;
	}

	public short getRemotePort() {
		return remotePort;
	}

}
