package userinterface;
import javax.swing.*;
import commonality.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import operation.ConnectSockets;

@SuppressWarnings("serial")
public class ChatInterface extends JFrame implements ActionListener
{
	JTextArea jta;
	JTextField jtf;
	JButton jb;
	JPanel jp;
	String ownerId;
	String friendId;
	String message;
		
	public ChatInterface(String ownerId ,String friendId)
	{
		this.ownerId=ownerId;
		this.friendId=friendId;
		jta=new JTextArea();
		jtf=new JTextField(15);
		jb=new JButton("����");
		jb.addActionListener(this);
		jp=new JPanel();
		jp.add(jtf);
		jp.add(jb);
		
		this.add(jta,"Center");
		this.add(jp,"South");
		this.setTitle(ownerId+"���ں�"+friendId+"����");
		this.setIconImage((new ImageIcon("image/qq.gif").getImage()));
		this.setSize(300, 200);
		this.setLocation(318,186);
		this.setResizable(false);
		this.setVisible(true);
	}
	public void showMessage(TransportObject m)
	{
		@SuppressWarnings("deprecation")
		String datetime=Calendar.getInstance().getTime().toLocaleString();
		message=m.getSender()+" ����˵:    "+(String)m.getObj()+"      "+datetime+"\r\n";
		jta.append(message);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb)
		{
			TransportObject m=new TransportObject();
			m.setMesType(MessageType.message_comm_mes);
			m.setSender(this.ownerId);
			m.setGetter(this.friendId);
			m.setObj(jtf.getText());
			m.setSendTime(new Date().toString());
			try {
				ObjectOutputStream oos=new ObjectOutputStream(ConnectSockets.linktoserver.getSocket().getOutputStream());
				oos.writeObject(m);
				@SuppressWarnings("deprecation")
				String mymessage="�Ҷ�"+m.getGetter()+"˵��  "+jtf.getText()+"      "+Calendar.getInstance().getTime().toLocaleString()+"\r\n";
				jta.append(mymessage);
				jtf.setText("");
			} catch (Exception e2) {}			
		}	
	}	
}//this.timeNow.setText("��ǰϵͳʱ�䣺"+Calendar.getInstance().getTime().toLocaleString()+" ");	