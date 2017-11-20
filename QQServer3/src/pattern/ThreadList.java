package pattern;
import java.util.*;

public class ThreadList 
{
	public static HashMap<String, HandlingThread> hm=new HashMap<String, HandlingThread>();
	public static void addThread(String uid,HandlingThread xt)
	{
		hm.put(uid, xt);
	}
	public static HandlingThread getThread(String uid)
	{
		return (HandlingThread)hm.get(uid);
	}
}