package operation;
import commonality.*;
import lists.*;
import java.net.*;
import java.io.*;

public class ConnectTools 
{
	public Socket s;
	public ConnectTools()
	{
		
	}
	public boolean sendMessage(Object o)
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
				LinkToServer linktoserver=new LinkToServer(s);
				linktoserver.start();
				Link.addLink(((User)o).getQQId(),linktoserver);
				b=true;
			}			
		} catch (Exception e) {}
		return b;
	}
}