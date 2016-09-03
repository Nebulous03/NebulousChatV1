package nebulous.networking;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import nebulous.chat.Console;

public class ClientConnection {
	
	private Thread SEND;
	private DatagramSocket socket;
	private ServerConnection server;
	private InetAddress address;
	private short port;
	
	public ClientConnection(ServerConnection server) {
		this.server = server; 
		this.port = server.getRemotePort();
	}
	
	public boolean createConnection(){
		try { 
			socket = new DatagramSocket(server.getRemotePort());
			address = InetAddress.getByName(server.getRemoteIP());
		} catch (Exception e) { 
			Console.printErr("UNABLE TO CONNECT TO " + server.getRemoteIP());
			e.printStackTrace(); 
			return false;
		} return true;
	}
	
	private void send(final byte[] data){
		SEND = new Thread("CLIENT-SEND"){
			public void run(){
				DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
				try {
					socket.send(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
	}
	
	private String revieve(){
		byte[] data = new byte[1024];
		DatagramPacket packt = new DatagramPacket(data, data.length);
		try {
			socket.receive(packt);
		} catch (IOException e) {
			e.printStackTrace();
		} return new String(packt.getData());
	}

	public DatagramSocket getSocket() {
		return socket;
	}

	public ServerConnection getServerConnection() {
		return server;
	}

	public InetAddress getAddress() {
		return address;
	}

	public short getPort() {
		return port;
	}

}
