package gongju;
import java.util.*;
public class Link {

	private static HashMap<String, LinkToServer> hm=new HashMap<String, LinkToServer>();
	
	public static void addLink(String ltId,LinkToServer link)
	{
		hm.put(ltId,link);
	}	
	public static LinkToServer getLink(String ltId)
	{
		return (LinkToServer)hm.get(ltId);
	}
}
