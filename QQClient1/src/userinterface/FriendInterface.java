package userinterface;
import javax.swing.*;
import commonality.*;
import gongju.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class FriendInterface extends JFrame implements ActionListener,MouseListener
{
	JPanel jphy1,jphy2,jphy3;
	JButton jphy_jb1,jphy_jb2,jphy_jb3;
	JScrollPane jsp1;
	String owner;		
	JPanel jpmsr1,jpmsr2,jpmsr3;
	JButton jpmsr_jb1,jpmsr_jb2,jpmsr_jb3;
	JScrollPane jsp2;
	JLabel []jb1s;	
	CardLayout cl;
	
	public void updateFriend(Message m)
	{
		String onLineFriend[]=m.getContent().split(" ");
		
		for(int i=0;i<onLineFriend.length;i++)
		{			
			jb1s[Integer.parseInt(onLineFriend[i])-1].setEnabled(true);
		}
	}	
	public FriendInterface(String ownerId)
	{
		jphy_jb1=new JButton("我的好友");
		jphy_jb2=new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3=new JButton("黑名单");
		jphy1=new JPanel(new BorderLayout());
		jphy2=new JPanel(new GridLayout(50,1,4,4));
		jb1s =new JLabel[50];
		
		for(int i=0;i<jb1s.length;i++)
		{
			jb1s[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jb1s[i].setEnabled(false);
			if(jb1s[i].getText().equals(ownerId))
			{
				jb1s[i].setEnabled(true);
			}
			jb1s[i].addMouseListener(this);
			jphy2.add(jb1s[i]);			
		}		
		jphy3=new JPanel(new GridLayout(2,1));
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jsp1=new JScrollPane(jphy2);
		
		jphy1.add(jphy_jb1,"North");
		jphy1.add(jsp1,"Center");
		jphy1.add(jphy3,"South");
		this.owner=ownerId;
		jpmsr_jb1=new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2=new JButton("陌生人");
		jpmsr_jb3=new JButton("黑名单");
		jpmsr1=new JPanel(new BorderLayout());
		
		jpmsr2=new JPanel(new GridLayout(20,1,4,4));
		
		JLabel []jb1s2=new JLabel[20];
		
		for(int i=0;i<jb1s2.length;i++)
		{
			jb1s2[i]=new JLabel(i+1+"",new ImageIcon("image/mm.jpg"),JLabel.LEFT);
			jpmsr2.add(jb1s2[i]);
		}		
		jpmsr3=new JPanel(new GridLayout(2,1));
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		jsp2=new JScrollPane(jpmsr2);
		jpmsr1.add(jpmsr3,"North");
		jpmsr1.add(jsp2,"Center");
		jpmsr1.add(jpmsr_jb3,"South");
		cl=new CardLayout();
		this.setLayout(cl);
		this.add(jphy1,"1");
		this.add(jpmsr1,"2");
		this.setTitle(ownerId);
		this.setSize(200, 400);
		this.setLocation(318,186);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jphy_jb2)
		{
			cl.show(this.getContentPane(), "2");
		}
		else if(e.getSource()==jpmsr_jb1)
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
			Chat.addChat(this.owner+" "+friendNo,chatInterface);
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
}