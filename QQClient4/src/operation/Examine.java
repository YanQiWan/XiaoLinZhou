package operation;
import commonality.*;

public class Examine 
{
	public boolean checkUser(User u)
	{
		return new ConnectSockets().sendLoginMessage(u);
	}
}
