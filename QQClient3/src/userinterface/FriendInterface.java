package userinterface;
import javax.swing.*;
import commonality.*;
import lists.*;
import operation.ConnectTools;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class FriendInterface extends JFrame implements ActionListener,MouseListener
{
	private static String ownerId=null;
	private static FriendInterface friendInterface=null;
	public static ArrayList<User> friendlist=null;
	public static FriendInterface getFriendInterface(String ownerId)
	{
		if(friendInterface==null)
		{
			friendInterface=new FriendInterface(ownerId);
			FriendInterface.ownerId=ownerId;
		}
		return friendInterface;
	}
	@SuppressWarnings("unchecked")
	public void updateFriend(TransportObject m)
	{
		friendlist=(ArrayList<User>)m.getObj();
		friendInterface=new FriendInterface(ownerId);
		this.dispose();
	}
	private JPanel jp_friendup,jp_friendmid,jp_frienddown;
	private JButton jb_friendup,jb_friendmid,jb_frienddown;
	private JScrollPane jsp_friend;
	private String owner;		
	private JPanel jp_strangeup,jp_strangemid,jp_strangedown;
	private JButton jb_strangeup,jb_strangemid,jb_strangedown;
	private JScrollPane jsp_strange;
	private JLabel []jb1s;	
	private CardLayout cl;

	
	private FriendInterface(String ownerId)
	{
		jb_friendup=new JButton("我的好友");
		jb_friendmid=new JButton("陌生人");
		jb_friendmid.addActionListener(this);
		jb_frienddown=new JButton("黑名单");
		jp_friendup=new JPanel(new BorderLayout());
		jp_friendmid=new JPanel(new GridLayout(50,1,4,4));
		//
		if(friendlist!=null)
		{
			System.out.println(friendlist.size()+"kkk");
			jb1s=new JLabel[friendlist.size()];
			for(int i=0;i<jb1s.length;i++)
			{
				jb1s[i]=new JLabel(friendlist.get(i).getQQId(),new ImageIcon("image/mm.jpg"),JLabel.LEFT);
				if(friendlist.get(i).isOnline()==0)
					jb1s[i].setEnabled(false);
				jb1s[i].addMouseListener(this);
				jp_friendmid.add(jb1s[i]);	
			}
		}
		jp_frienddown=new JPanel(new GridLayout(2,1));
		jp_frienddown.add(jb_friendmid);
		jp_frienddown.add(jb_frienddown);
		jsp_friend=new JScrollPane(jp_friendmid);
		
		jp_friendup.add(jb_friendup,"North");
		jp_friendup.add(jsp_friend,"Center");
		jp_friendup.add(jp_frienddown,"South");
		this.owner=ownerId;
		jb_strangeup=new JButton("我的好友");
		jb_strangeup.addActionListener(this);
		jb_strangemid=new JButton("陌生人");
		jb_strangedown=new JButton("黑名单");
		jp_strangeup=new JPanel(new BorderLayout());
		
		jp_strangemid=new JPanel(new GridLayout(20,1,4,4));
		
		JLabel []jb1s2=new JLabel[20];
		
		for(int i=0;i<jb1s2.length;i++)
		{
			jb1s2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jp_strangemid.add(jb1s2[i]);
		}		
		jp_strangedown=new JPanel(new GridLayout(2,1));
		jp_strangedown.add(jb_strangeup);
		jp_strangedown.add(jb_strangemid);
		jsp_strange=new JScrollPane(jp_strangemid);
		jp_strangeup.add(jp_strangedown,"North");
		jp_strangeup.add(jsp_strange,"Center");
		jp_strangeup.add(jb_strangedown,"South");
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jp_friendup,"1");
		this.add(jp_strangeup,"2");
		this.setTitle(ownerId);
		this.setSize(200, 400);
		this.setLocation(318,186);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jb_friendmid)
		{
			cl.show(this.getContentPane(), "2");
		}
		else if(e.getSource()==jb_strangeup)
		{
			cl.show(this.getContentPane(), "1");
		}	
	}
	public void mouseClicked(MouseEvent e)
	{
		if(e.getClickCount()==2)
		{
			String friendNo=((JLabel)e.getSource()).getText();
			ChatInterface chatInterface=new ChatInterface(this.owner,friendNo);
			ChatLists.addChat(this.owner+" "+friendNo,chatInterface);
		}
	}	
	public void mouseEntered(MouseEvent e) 
	{
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.red);	
	}
	public void mouseExited(MouseEvent e) 
	{
		JLabel jl=(JLabel)e.getSource();
		jl.setForeground(Color.black);
	}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e){}
	public void dispose()
	{
		/*TransportObject send_m=new TransportObject();
		send_m.setMesType(MessageType.message_log_out);
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(ConnectTools.linktoserver.getSocket().getOutputStream());
			send_m.setObj(FriendInterface.ownerId);
			oos.writeObject(send_m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		super.dispose();
	}
}