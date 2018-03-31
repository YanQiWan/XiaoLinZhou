package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import filemanager.Directory;
import filemanager.FileController;
import processmanager.FileEvent;
import processmanager.Group;
import processmanager.MkdirEvent;
import processmanager.PCB;
import processmanager.Process;
import processmanager.ProcessController;
import processmanager.ProcessEvent;
import processmanager.Semaphore;
import processmanager.TouchEvent;

@SuppressWarnings("serial")
public class MainInterface extends JFrame implements ActionListener, MouseListener{

	private static MainInterface mainInterface = null;
	public static MainInterface getInstance() {
		if (mainInterface == null)
		{
			mainInterface = new MainInterface();
			ProcessController.getProcessController();
		}
		return mainInterface;
	}
	public static List<Semaphore> semList=new ArrayList<Semaphore>();
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==jb_pci)
		{
			ProcessControllerInterface.getInstance().setVisible(true);
		}
		else if(e.getSource()==jb_mci)
		{
			MemoryControllerInterface.getInstance().setVisible(true);
		}
		else if(e.getSource()==jb_fci)
		{
			FileControllerInterface.getInstance().setVisible(true);
		}else if(e.getSource()==jb_pro){
			AddProcessInterface.getInstance().setVisible(true);
		}
		else if(e.getSource()==jb_sem)
		{
			AddSemaphoreInterface.getInstance().setVisible(true);
		}
	}
	
	
	private JPanel jp_main;
	private JLabel jl_title;
	private JButton jb_pci,jb_mci,jb_fci,jb_sem,jb_pro;
	private MainInterface() {
		jl_title=new JLabel("Wanix");
		jl_title.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		
		jb_pci=new JButton("进程管理器");
		jb_pci.addActionListener(this);
		jb_mci=new JButton("内存管理器");
		jb_mci.addActionListener(this);
		jb_fci=new JButton("文件管理器");
		jb_fci.addActionListener(this);
		jb_pro=new JButton("添加新进程");
		jb_pro.addActionListener(this);
		jb_sem=new JButton("添加信号量");
		jb_sem.addActionListener(this);
		jp_main = new JPanel(new GridLayout(6, 1));
		jp_main.add(jl_title);
		jp_main.add(jb_pci);
		jp_main.add(jb_mci);
		jp_main.add(jb_fci);
		jp_main.add(jb_pro);
		jp_main.add(jb_sem);

		this.add(jp_main);
		this.setSize(400, 520);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String args[])
	{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainInterface.getInstance();
			}
		}).start();
	}
}
