package operation;
import commonality.*;
import java.net.*;
import java.io.*;
import gongju.*;

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
			s=new Socket("101.200.38.1",8888);		
			ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
			oos.writeObject(o);
            ObjectInputStream ois=new ObjectInputStream(s.getInputStream());			
			Message ms=(Message)ois.readObject();
			if(ms.getMesType().equals("1"))
			{	
				LinkToServer linktoserver=new LinkToServer(s);
				linktoserver.start();
				Link.addLink(((User)o).getUserId(),linktoserver);
				b=true;
			}			
		} catch (Exception e) {}
		return b;
	}
}