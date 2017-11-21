package operation;
import java.util.*;
import commonality.*;
import lists.OnlineUserList;
import java.net.*;
import java.io.*;

public class HandlingThread extends Thread
{
    private Socket s;
	public HandlingThread(Socket s)
	{
		this.s=s;
	}
	public Socket getS() {
		return s;
	}
	public void setS(Socket s) {
		this.s = s;
	}
	public void run()
	{		
		while(true)
		{
			try {
				ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
				TransportObject get_m=(TransportObject)ois.readObject();
				if(get_m.getMesType().equals(MessageType.message_comm_mes))
				{
					HandlingThread getter=OnlineUserList.getThread(get_m.getGetter());
					ObjectOutputStream oos=new ObjectOutputStream(getter.s.getOutputStream());
					oos.writeObject(get_m);
				}
				else if(get_m.getMesType().equals(MessageType.message_get_onLineFriend))
				{
					try {
						TransportObject send_m=new TransportObject();
						send_m.setMesType(MessageType.message_ret_onLineFriend);
						System.out.println(get_m.getSender());
						ArrayList<User> friendlist=DataBaseConnect.GetFriendList(get_m.getSender());
						ObjectOutputStream oos=new ObjectOutputStream(OnlineUserList.getThread(get_m.getSender()).s.getOutputStream());
						send_m.setObj(friendlist);
						send_m.setGetter(get_m.getSender());
						oos.writeObject(send_m);
					} catch (Exception e) {}
				}
				else if(get_m.getMesType().equals(MessageType.message_log_out))
				{
					String QQId=get_m.getSender();//要登离的QQId
					if(DataBaseConnect.LogOut(QQId))
						System.out.println("数据修改成功,用户"+QQId+"已退出");
					else
						System.out.println("数据修改失败,用户"+QQId+"退出失败");
				}
			} catch (Exception e) {}			
		}		
	}
}