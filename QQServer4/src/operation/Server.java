package operation;
import java.net.*;
import java.util.ArrayList;
import java.io.*;

import commonality.*;
import lists.OnlineUserList;

public class Server 
{
	public Server()
	{
        try {
			@SuppressWarnings("resource")
			ServerSocket ss=new ServerSocket(8888);
			while(true)
			{
				Socket s=ss.accept();
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				User u=(User)ois.readObject();
				TransportObject m=new TransportObject();
				ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
				if(DataBaseConnect.Examine(u))
				{
					m.setMesType("1");
					oos.writeObject(m);
					HandlingThread onlineUser=new HandlingThread(s);
					OnlineUserList.addThread(u.getQQId(), onlineUser);
					onlineUser.start();
					//这里要提醒你的好友修改他们的好友列表
					ArrayList<User> friendList=DataBaseConnect.GetFriendList(u.getQQId());
					for(int i=0;i<friendList.size();i++)
					{
						if(friendList.get(i).isOnline()!=0)
						{
							ObjectOutputStream osi=new ObjectOutputStream(OnlineUserList.getThread(friendList.get(i).getQQId()).getS().getOutputStream());
							System.out.println(friendList.get(i).getQQId()+"需要修改");
							TransportObject mi=new TransportObject();
							mi.setMesType(MessageType.change_friend_list);
							osi.writeObject(mi);
						}
					}
				}	
				else
				{
					m.setMesType("2");
					oos.writeObject(m);
					s.close();
				}				
			}				
		} catch (Exception e) {}
	}
}
