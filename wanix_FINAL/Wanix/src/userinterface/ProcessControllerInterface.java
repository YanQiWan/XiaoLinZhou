package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import processmanager.*;
import processmanager.Process;

@SuppressWarnings("serial")
public class ProcessControllerInterface extends JFrame implements ActionListener, MouseListener {

	private static ProcessControllerInterface processControllerInterface = null;

	public static ProcessControllerInterface getInstance() {
		if (processControllerInterface == null) {
			processControllerInterface = new ProcessControllerInterface();
			processControllerInterface.setVisible(false);
		}
		return processControllerInterface;
	}

	public void updateProcessControllerInterface() {
		jtf_ready.setText("");
		jtf_block.setText("");
		jl_running.setText("");
		for (int i = 0; i < ready_queue.size(); i++) {
			jtf_ready.append(ready_queue.get(i).pcb.getName() + "\n");
		}
		for (int i = 0; i < block_queue.size(); i++) {
			jtf_block.append(block_queue.get(i).pcb.getName() + "\n");
		}
		Process p = ProcessController.getRunningProcess();
		if (p != null)
			jl_running.setText(p.pcb.getName());
		else {
			jl_running.setText("没有正在运行的进程!");
		}
	}

	public void updateProcessControllerLog(String newlog) {
		jtf_log.append(newlog + "\n");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private JPanel jp_main;
	private JPanel jp_ready, jp_block, jp_running, jp_log;
	private JLabel jl_running;
	private JTextArea jta_ready, jta_block, jta_running, jta_log;
	private JTextArea jtf_ready, jtf_block, jtf_log;
	private JScrollPane jsp_ready, jsp_block;
	private List<Process> ready_queue = null;
	private List<Process> block_queue = null;

	private ProcessControllerInterface() {
		jp_ready = new JPanel(new BorderLayout());
		jta_ready = new JTextArea();
		jta_ready.setText("就绪队列:");

		ready_queue = ProcessController.getReady_queue();
		jtf_ready = new JTextArea();

		jsp_ready = new JScrollPane(jtf_ready);
		jp_ready.add(jta_ready, "North");
		jp_ready.add(jsp_ready, "Center");

		jp_block = new JPanel(new BorderLayout());
		jta_block = new JTextArea("阻塞队列:");

		block_queue = ProcessController.getBlock_queue();
		jtf_block = new JTextArea();

		jsp_block = new JScrollPane(jtf_block);
		jp_block.add(jta_block, "North");
		jp_block.add(jsp_block, "Center");

		jp_running = new JPanel(new BorderLayout());
		jta_running = new JTextArea("正在运行:", 1, 1);
		jl_running = new JLabel();

		jsp_ready = new JScrollPane(jtf_ready);
		jp_ready.add(jta_ready, "North");
		jp_ready.add(jsp_ready, "Center");

		jp_log = new JPanel(new BorderLayout());
		jta_log = new JTextArea();
		jta_log.setText("日志:");
		jtf_log = new JTextArea();
		jp_log.add(jta_log, "North");
		jp_log.add(new JScrollPane(jtf_log), "Center");

		this.updateProcessControllerInterface();

		jl_running.addMouseListener(this);
		jp_running.add(jta_running, "North");
		jp_running.add(jl_running, "Center");

		jp_main = new JPanel(new GridLayout(4, 1));
		jp_main.add(jp_ready);
		jp_main.add(jp_block);
		jp_main.add(jp_running);
		jp_main.add(jp_log);

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
