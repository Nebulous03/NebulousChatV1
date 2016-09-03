package nebulous.networking.server;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
	
	private DatagramSocket socket;
	private short port;

	public Server(short port) {
		this.port = port;
		try {
			this.socket = new DatagramSocket(port);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

}
