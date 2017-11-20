package pattern;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import commonality.User;

public class DataBaseConnect {
	static Connection connection=null;
	static Statement statement=null;
	static PreparedStatement ps=null;
	static String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=QQChat";
	static String userName="sa";
	static String userPwd="software+2015";
	static
	{
		try
		{
			Class.forName(driverName);
			System.out.println("加载驱动成功！");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("加载驱动失败！");
		}
		try
		{
			connection=DriverManager.getConnection(dbURL,userName,userPwd);
			System.out.println("连接数据库成功！");
		}catch(Exception e)
		{
			e.printStackTrace();
			System.out.print("SQL Server连接失败！");
		}	
		try {
			statement=connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//建立Statement对象
	}
	public static boolean Examine(User user) throws SQLException
	{
		String sql="select * from UserInfo where QQId=? and Passwd=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, user.getQQId());
		ps.setString(2, user.getPasswd());
		ResultSet resultset=ps.executeQuery();
		if(resultset.next())
		{
			String changesql="UPDATE UserInfo SET Onlinestate=? WHERE QQId=?";
			PreparedStatement ps_change = connection.prepareStatement(changesql);
			ps_change.setInt(1, 1);
			ps_change.setString(2, user.getQQId());
			int succeed=ps_change.executeUpdate();
			ps_change.close();
			if(succeed!=0)
			{
				return true;
			}
		}
		return false;
	}
	public static ArrayList<User> GetFriendList(String QQId) throws SQLException
	{
		ArrayList<User> friendlist=new ArrayList<User>();
		String sql="select QQId,Onlinestate from UserInfo where UserId in (select Friend_Id from FriendShip where UserId=(select UserId from UserInfo where QQId=?))";
		System.out.println(sql);
		ps = connection.prepareStatement(sql);
		ps.setString(1, QQId);
		ResultSet resultset=ps.executeQuery();
		while(resultset.next())
		{
			System.out.println("shit");
			User friend=new User();
			friend.setQQId(resultset.getString(1));
			friend.setOnline(resultset.getInt(2));
			System.out.println(resultset.getString("QQId")+resultset.getInt("Onlinestate"));
			friendlist.add(friend);
		}
		return friendlist;
	}
}
