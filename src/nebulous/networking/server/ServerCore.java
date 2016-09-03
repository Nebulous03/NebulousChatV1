package nebulous.networking.server;

import nebulous.chat.Console;

public class ServerCore {

	public static Server server;
	
	public static void main(String[] args){
		if(args.length != 1){ 
			Console.println("Usage: java -jar NebulousChat.jar [port]"); 
			return; 
		}
		
		short port = 0;
		port = Short.parseShort(args[0]);
		server = new Server(port);
	}

}
