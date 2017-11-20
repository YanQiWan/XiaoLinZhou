package operation;
import commonality.*;

public class Examine 
{
	public boolean checkUser(User u)
	{
		return new ConnectTools().sendLoginMessage(u);
	}
}
