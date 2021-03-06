package operation;
import java.io.*;
import java.net.*;
import commonality.*;
import lists.ChatLists;
import userinterface.*;


public class LinkToServer extends Thread
{
    private Socket s;
    private FriendInterface friendInterface=null;
    private static LinkToServer linktoserver=null;
	private static String ownerId=null;
	public static LinkToServer buildLink(String ownerId,Socket s)
	{
		if(linktoserver==null)
		{
			linktoserver=new LinkToServer(s);
			LinkToServer.ownerId=ownerId;
		}
		return linktoserver;
	}
	private LinkToServer(Socket s)
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
				TransportObject m=(TransportObject)ois.readObject();
				if(m.getMesType().equals(MessageType.message_comm_mes))
				{				
					ChatInterface chat= ChatLists.getChat(m.getGetter()+" "+m.getSender());
					chat.showMessage(m);
				}else if(m.getMesType().equals(MessageType.message_ret_onLineFriend))
				{
					String getter=m.getGetter();
					System.out.println("getter="+getter);
					friendInterface=FriendInterface.getFriendInterface(getter);
					if(friendInterface !=null)
					{
						System.out.println("shit");
						friendInterface.updateFriend(m);
					}				
				}else if(m.getMesType().equals(MessageType.change_friend_list))
				{
					ObjectOutputStream oos=new ObjectOutputStream(ConnectSockets.linktoserver.getSocket().getOutputStream());
					TransportObject m1=new TransportObject();
					m1.setMesType(MessageType.message_get_onLineFriend);
					m1.setSender(ownerId);
					oos.writeObject(m1);	
				}
			} catch (Exception e) {}
		}
	}
}
