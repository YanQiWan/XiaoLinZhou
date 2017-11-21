package lists;
import java.util.*;
import userinterface.*;
public class ChatLists {

	private static HashMap<String, ChatInterface> chat=new HashMap<String, ChatInterface>();
	
	public static void addChat(String friendId,ChatInterface chatInterface)
	{
		chat.put(friendId, chatInterface);
	}
	public static ChatInterface getChat(String friendId)
	{
		return (ChatInterface)chat.get(friendId);
	}	
}
