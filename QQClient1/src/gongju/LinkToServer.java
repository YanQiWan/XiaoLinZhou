package gongju;
import java.io.*;
import java.net.*;

import commonality.*;
import userinterface.*;


public class LinkToServer extends Thread
{
    private Socket s;
	
	public LinkToServer(Socket s)
	{
		this.s=s;
	}
	public Socket getSocket() {
		return s;
	}

	public void setSocket(Socket s) {
		this.s = s;
	}
	public void run()
	{
		while(true)
		{
			try {
				
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				Message m=(Message)ois.readObject();
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{				
					ChatInterface lt= Chat.getChat(m.getGetter()+" "+m.getSender());
					lt.showMessage(m);
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{
					String getter=m.getGetter();
					System.out.println("getter="+getter);
					FriendInterface friendInterface=Friend.getQqFriendList(getter);
					if(friendInterface !=null)
					{
						friendInterface.updateFriend(m);
					}				
				}
			} catch (Exception e) {}
		}
	}
}
