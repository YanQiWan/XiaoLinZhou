package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import processmanager.Process;
import processmanager.ProcessController;
import processmanager.Semaphore;

public class AddSemaphoreInterface extends JFrame implements ActionListener, MouseListener {

	private static AddSemaphoreInterface addSemaphoreInterface = null;

	public static AddSemaphoreInterface getInstance() {
		if (addSemaphoreInterface == null) {
			addSemaphoreInterface = new AddSemaphoreInterface();
			addSemaphoreInterface.setVisible(false);
		}
		return addSemaphoreInterface;
	}

	public void updateAddSemaphoreInterface() {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb_add) {
			String name;
			int init;
			if ((name = jtf_name.getText()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "信号量名不能为空！");
			} else if ((init = Integer.parseInt(jtf_init.getText())) < 0) {
				JOptionPane.showMessageDialog(null, "信号量初始值不能为负！");
			} else {
				MainInterface.semList.add(new Semaphore(name, init));
				JOptionPane.showMessageDialog(null, "信号量添加成功！");
				AddProcessInterface.getInstance().updateSemList();
				jtf_name.setText("");
				jtf_init.setText("");
			}
		}
	}

	private JPanel jp_main;
	private JTable jt_sem;
	private JLabel jl_name, jl_init;
	private JTextField jtf_name, jtf_init;
	private JButton jb_add;

	private AddSemaphoreInterface() {
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

		jl_name = new JLabel("信号量名");

		jtf_name = new JTextField();
		jtf_name.addActionListener(this);

		jl_init = new JLabel("初始值");

		jtf_init = new JTextField();
		jtf_init.addActionListener(this);

		jb_add = new JButton("添加");
		jb_add.addActionListener(this);

		jp_main = new JPanel(new GridLayout(5, 1));
		// jp_main.add(jt_sem);
		jp_main.add(jl_name);
		jp_main.add(jtf_name);
		jp_main.add(jl_init);
		jp_main.add(jtf_init);
		jp_main.add(jb_add);

		updateAddSemaphoreInterface();

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
