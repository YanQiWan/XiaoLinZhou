package operation;
import commonality.*;
import java.net.*;
import java.io.*;

public class ConnectSockets 
{
	public static LinkToServer linktoserver=null;
	public Socket s;
	public ConnectSockets()
	{
		
	}
	public boolean sendLoginMessage(Object o)
	{
		boolean b=false;
		try {
			s=new Socket("127.0.0.1",8888);	//	101.200.38.1
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());			
			TransportObject ms=(TransportObject)ois.readObject();
			if(ms.getMesType().equals(MessageType.message_succeed))
			{	
				linktoserver=LinkToServer.buildLink(((User)o).getQQId(), s);
				linktoserver.start();
				b=true;
			}			
		} catch (Exception e) {}
		return b;
	}
}