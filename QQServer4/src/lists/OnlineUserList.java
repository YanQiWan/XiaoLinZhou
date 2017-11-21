package lists;
import java.util.*;

import operation.HandlingThread;

public class OnlineUserList 
{
	public static HashMap<String, HandlingThread> chatlist=new HashMap<String, HandlingThread>();
	public static void addThread(String uid,HandlingThread xt)
	{
		chatlist.put(uid, xt);
	}
	public static HandlingThread getThread(String uid)
	{
		return (HandlingThread)chatlist.get(uid);
	}
}