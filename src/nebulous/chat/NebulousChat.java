package nebulous.chat;

public class NebulousChat {
	
	public static LoginWindow login;
	public static ClientWindow window;

	public static void main(String[] args) {
		Console.specialPrintln("[CLIENT] Nebulous Chat - Client");
		Console.specialPrintln("[CLIENT] Starting up...");
		
		openLoginBox();
	}
	
	public static void openLoginBox(){
		Console.specialPrintln("[CLIENT] Attempting Login...");
		login = new LoginWindow();
	}
	
	public static void openChatWindow(User user){
		Console.specialPrintln("[CLIENT] Opening Chat Window...");
		window = new ClientWindow(user);
	}
	
}
