package pattern;
import java.net.*;
import java.io.*;

import commonality.*;

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
					HandlingThread chat=new HandlingThread(s);
					ThreadList.addThread(u.getQQId(), chat);
					chat.start();
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
