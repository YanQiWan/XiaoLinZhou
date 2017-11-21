package commonality;

@SuppressWarnings("serial")
public class User implements java.io.Serializable
{
	private String QQId;
	private String Passwd;
	private int UserId;
	private int Online;
	public int isOnline() {
		return Online;
	}
	public void setOnline(int online) {
		Online = online;
	}
	public int getUserId() {
		return UserId;
	}
	public void setUserId(int userId) {
		UserId = userId;
	}
	public String getQQId() {
		return QQId;
	}
	public void setQQId(String QQId) {
		this.QQId = QQId;
	}
	public String getPasswd() {
		return Passwd;
	}
	public void setPasswd(String passwd) {
		Passwd = passwd;
	}
}
