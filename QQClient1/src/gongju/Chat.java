package gongju;
import java.util.*;
import userinterface.*;
public class Chat {

	private static HashMap<String, ChatInterface> hm=new HashMap<String, ChatInterface>();
	
	public static void addChat(String haoyou,ChatInterface chatInterface)
	{
		hm.put(haoyou, chatInterface);
	}
	public static ChatInterface getChat(String friend)
	{
		return (ChatInterface)hm.get(friend);
	}	
}
