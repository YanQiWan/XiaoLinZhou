package gongju;
import java.util.*;

import userinterface.*;

import java.io.*;

public class Friend 
{
    private static HashMap<String, FriendInterface> hm=new HashMap<String,FriendInterface>();
	public static void addlist(String lt,FriendInterface friendlist)
	{
		hm.put(lt,friendlist);
	}
	
	public static FriendInterface getQqFriendList(String lt)
	{
		return (FriendInterface)hm.get(lt);
	}
}
