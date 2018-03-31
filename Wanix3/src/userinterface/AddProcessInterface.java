package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import processmanager.Process;
import processmanager.ProcessController;
import processmanager.Semaphore;

public class AddProcessInterface extends JFrame implements ActionListener, MouseListener {

	private static AddProcessInterface addProcessInterface = null;

	public static AddProcessInterface getInstance() {
		if (addProcessInterface == null) {
			addProcessInterface = new AddProcessInterface();
			addProcessInterface.setVisible(false);
		}
		return addProcessInterface;
	}

	public void updateAddProcessInterface() {
		// jtf_ready.setText("");
		// jtf_block.setText("");
		// jl_running.setText("");
		// for (int i = 0; i < ready_queue.size(); i++) {
		// jtf_ready.append(ready_queue.get(i).pcb.getName() + "\n");
		// }
		// for (int i = 0; i < block_queue.size(); i++) {
		// jtf_block.append(block_queue.get(i).pcb.getName() + "\n");
		// }
		// Process p=ProcessController.getRunningProcess();
		// if (p != null)
		// jl_running.setText(p.pcb.getName());
		// else {
		// jl_running.setText("没有正在运行的进程!");
		// }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	private JPanel jp_main;
	private JTable jt_events;
	private JLabel jl_type, jl_time, jl_size,jl_sem;
	private JComboBox<String> jcb_type;
	private JTextField jtf_time, jtf_size;
	private JButton jb_add,jb_sem;

	private AddProcessInterface() {
		/*
		 * jp_ready = new JPanel(new BorderLayout()); jta_ready = new
		 * JTextArea(); jta_ready.setText("就绪队列:");
		 * 
		 * ready_queue = ProcessController.getReady_queue(); jtf_ready=new
		 * JTextArea();
		 * 
		 * jsp_ready = new JScrollPane(jtf_ready); jp_ready.add(jta_ready,
		 * "North"); jp_ready.add(jsp_ready, "Center");
		 * 
		 * jp_block = new JPanel(new BorderLayout()); jta_block = new
		 * JTextArea("阻塞队列:");
		 * 
		 * block_queue = ProcessController.getBlock_queue(); jtf_block=new
		 * JTextArea();
		 * 
		 * jsp_block = new JScrollPane(jtf_block); jp_block.add(jta_block,
		 * "North"); jp_block.add(jsp_block, "Center");
		 * 
		 * jp_running = new JPanel(new BorderLayout()); jta_running = new
		 * JTextArea("正在运行:", 1, 1); jl_running = new JLabel();
		 * 
		 * updateProcessControllerInterface();
		 * 
		 * jl_running.addMouseListener(this); jp_running.add(jta_running,
		 * "North"); jp_running.add(jl_running, "Center");
		 */

		// jt_sem=new JTable();

		jl_type=new JLabel("事件类型");
		
		jcb_type=new JComboBox<String>();
		jcb_type.addItem("普通事件(Normal)");
		jcb_type.addItem("等待事件(Wait)");
		jcb_type.addItem("信号事件(Signal)");
		jcb_type.addActionListener(this);
		
		jl_time=new JLabel("事件时间");
		
		jtf_time=new JTextField();
		jtf_time.addActionListener(this);
		
		jl_size=new JLabel("事件内存大小");
		
		jtf_size=new JTextField();
		jtf_size.addActionListener(this);
		
		jl_sem=new JLabel("信号量");
		
		jb_sem=new JButton("选择信号量");
		jb_sem.addActionListener(this);
		
		jb_add=new JButton("添加");
		jb_add.addActionListener(this);
		
		jp_main = new JPanel(new GridLayout(5, 2));
		// jp_main.add(jt_events);
		jp_main.add(jl_type);
		jp_main.add(jcb_type);
		jp_main.add(jl_time);
		jp_main.add(jtf_time);
		jp_main.add(jl_size);
		jp_main.add(jtf_size);
		jp_main.add(jl_sem);
		jp_main.add(jb_sem);
		jp_main.add(jb_add);

		updateAddProcessInterface();

		this.add(jp_main);
		this.setSize(400, 520);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
}
