package userinterface;
import java.io.*;
import javax.swing.*;
import commonality.*;
import lists.*;
import java.awt.*;
import java.awt.event.*;
import operation.*;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener
{
	JLabel jbl1;
	JTabbedPane jtp;
	JPanel jp2,jp3,jp4;
	JLabel jp2_jbl1,jp2_jbl2,jp2_jbl3,jp2_jbl4;
	JButton jp2_jb1;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1,jp2_jcb2;
	JPanel jp1;
	JButton jp1_jb1,jp1_jb2,jp1_jb3;
	
	public static void main(String[] args)
	{
		new Login();
	}
	public Login()
	{
		jbl1=new JLabel(new ImageIcon("image/tou.gif"));
		jp2=new JPanel(new GridLayout(3,3));
		jp2_jbl1=new JLabel("QQ号码",JLabel.CENTER);
		jp2_jbl2=new JLabel("QQ密码",JLabel.CENTER);
		jp2_jbl3=new JLabel("忘记密码",JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4=new JLabel("申请密码保护",JLabel.CENTER);
		jp2_jb1=new JButton(new ImageIcon("image/clear.gif"));
		jp2_jtf=new JTextField();
		jp2_jpf=new JPasswordField();
		jp2_jcb1=new JCheckBox("隐身登录");
		jp2_jcb2=new JCheckBox("记住密码");
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jb1);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		jp2.add(jp2_jbl4);
		jtp=new JTabbedPane();
		jtp.add("QQ号码",jp2);
		jp3= new JPanel();
		jtp.add("手机号码",jp3);
		jp4=new JPanel();
		jtp.add("电子邮件",jp4);
		jp1=new JPanel();
		jp1_jb1=new JButton(new ImageIcon("image/denglu.gif"));
		jp1_jb1.addActionListener(this);
		jp1_jb2=new JButton(new ImageIcon("image/quxiao.gif"));
		jp1_jb3=new JButton(new ImageIcon("image/xiangdao.gif"));
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		jp1.add(jp1_jb3);
		this.add(jbl1,"North");
		this.add(jtp,"Center");
		this.add(jp1,"South");
		ImageIcon tp1=new ImageIcon("image/qq.gif");
		this.setIconImage(tp1.getImage());
		this.setTitle("用户登录");
		this.setSize(351,240);
		this.setLocation(300,280);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);	
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==jp1_jb1)
		{
			Examine yz=new Examine();
			User u=new User();//保存下输入的登录信息
			u.setQQId(jp2_jtf.getText().trim());
			u.setPasswd(new String(jp2_jpf.getPassword()));
			if(yz.checkUser(u))
			{
				try {
					FriendInterface.getFriendInterface(u.getQQId());//产生好友列表
					ObjectOutputStream oos=new ObjectOutputStream(Link.getLink(u.getQQId()).getSocket().getOutputStream());
					TransportObject m=new TransportObject();
					m.setMesType(MessageType.message_get_onLineFriend);
					m.setSender(u.getQQId());
					oos.writeObject(m);	
				} catch (Exception e3){}
				this.dispose();
			}
			else{
				JOptionPane.showMessageDialog(this,"用户名或密码错误");
			}
		}
	}
}