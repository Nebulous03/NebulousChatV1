package nebulous.chat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Console {
	
	public static final String PREFIX = "";

	private static DateFormat format = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	private static Date date = new Date();
	
	public static String getDate(){
		return format.format(date);
	}
	
	public static void println(Object text){
		System.out.println(PREFIX + text);
	}
	
	public static void print(Object text){
		System.out.print(PREFIX + text);
	}
	
	public static void printNative(Object text){
		System.out.print(text);
	}
	
	public static void printErr(Object text){
		System.err.println(PREFIX + text);
	}
	
	public static void specialPrintln(Object text){
		System.out.println("[" + Console.getDate() + "] " + text);
	}
	
	public static void printMOTD(String text){
		System.out.print(	
				
		"-------------------------------------------\n" +
		text + " \n" +
		"-------------------------------------------\n"
		
		);
	}
	

}
