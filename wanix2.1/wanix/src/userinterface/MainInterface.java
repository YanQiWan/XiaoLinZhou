package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import filemanager.Directory;
import filemanager.FileController;
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
	private JButton jb_pci,jb_mci,jb_fci,jb_sem,jb_pro;
	private MainInterface() {
		
		jb_pci=new JButton("���̹�����");
		jb_pci.addActionListener(this);
		jb_mci=new JButton("�ڴ������");
		jb_mci.addActionListener(this);
		jb_fci=new JButton("�ļ�������");
		jb_fci.addActionListener(this);
		jb_pro=new JButton("����½���");
		jb_pro.addActionListener(this);
		jb_sem=new JButton("����ź���");
		jb_sem.addActionListener(this);
		jp_main = new JPanel(new GridLayout(5, 1));
		jp_main.add(jb_pci);
		jp_main.add(jb_mci);
		jp_main.add(jb_fci);
		jp_main.add(jb_pro);
		jp_main.add(jb_sem);

		this.add(jp_main);
		this.setSize(200, 200);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setTitle("Wanix");
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
		ProcessController pc = ProcessController.getProcessController();
		Directory root = FileController.root;
		PCB pcb1 = new PCB("ProcessOwner", Group.OWNER, root);
		PCB pcb2 = new PCB("ProcessGroup", Group.GROUP, root);
		PCB pcb3 = new PCB("ProcessOthers", Group.OTHERS,root);

		List<ProcessEvent> events1 = new ArrayList<ProcessEvent>();
		List<ProcessEvent> events2 = new ArrayList<ProcessEvent>();
		List<ProcessEvent> events3 = new ArrayList<ProcessEvent>();
		
		events1.add(new TouchEvent("DOC_OWNER",root));

		events2.add(new TouchEvent("DOC_GROUP",root));
		
		events3.add(new TouchEvent("DOC_OTHERS",root));
		
		events1.add(new MkdirEvent("DIR_OWNER",root));
		
		Process p1 = new Process(pcb1, events1);
		Process p2 = new Process(pcb2, events2);
		Process p3 = new Process(pcb3, events3);

		pc.create(p1);
		pc.create(p2);
		pc.create(p3);
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				MainInterface.getInstance();
			}
		}).start();
	}
}
