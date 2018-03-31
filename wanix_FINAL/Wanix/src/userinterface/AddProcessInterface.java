package userinterface;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import filemanager.FileController;
import memorymanager.PartitionItem;
import processmanager.Group;
import processmanager.NormalEvent;
import processmanager.PCB;
import processmanager.Process;
import processmanager.ProcessController;
import processmanager.ProcessEvent;
import processmanager.Semaphore;
import processmanager.SignalEvent;
import processmanager.WaitEvent;

public class AddProcessInterface extends JFrame implements ActionListener, MouseListener {

	private static AddProcessInterface addProcessInterface = null;

	public static AddProcessInterface getInstance() {
		if (addProcessInterface == null) {
			addProcessInterface = new AddProcessInterface();
			addProcessInterface.setVisible(false);
		}
		return addProcessInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jcb_type) {
			switch (jcb_type.getSelectedIndex()) {
			case 0: {
				jcb_sem.setEnabled(false);
				jtf_time.setEnabled(true);
				jtf_size.setEnabled(true);
				break;
			}
			case 1:
			case 2: {
				jcb_sem.setEnabled(true);
				jtf_time.setEnabled(false);
				jtf_size.setEnabled(false);
				break;
			}
			default:
				break;
			}
		} else if (e.getSource() == jb_add) {
			boolean flag = false;
			if (jcb_type.getSelectedIndex() == 0) {
				int time = -1, size = -1;
				if (!jtf_time.getText().isEmpty())
					time = Integer.parseInt(jtf_time.getText());
				if (!jtf_time.getText().isEmpty())
					size = Integer.parseInt(jtf_size.getText());
				if (flag = (time > 0 && size > 0)) {
					eventslist.add(new NormalEvent(time, size));
					jtf_time.setText("");
					jtf_size.setText("");
				} else {
					if (time <= 0 || jtf_time.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "事件时间必须为正！");
					} else if (size <= 0 || jtf_size.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "事件内存大小必须为正！");
					}
				}
			} else if (jcb_type.getSelectedIndex() == 1) {
				if (flag = (!MainInterface.semList.isEmpty())) {
					eventslist.add(new WaitEvent(MainInterface.semList.get(jcb_sem.getSelectedIndex())));
				} else {
					JOptionPane.showMessageDialog(null, "信号量列表为空！");
				}
			} else if (jcb_type.getSelectedIndex() == 2) {
				if (flag = (!MainInterface.semList.isEmpty())) {
					eventslist.add(new SignalEvent(MainInterface.semList.get(jcb_sem.getSelectedIndex())));
				} else {
					JOptionPane.showMessageDialog(null, "信号量列表为空！");
				}
			}
			if (flag)
				updateEvents();
		} else if (e.getSource() == jb_ok) {
			if (!jtf_name.getText().isEmpty()) {
				PCB pcb = new PCB(jtf_name.getText(), Group.OWNER, FileController.root);
				ProcessController.getProcessController().create(new Process(pcb, eventslist));
				eventslist = new ArrayList<ProcessEvent>();
				jtf_event.setText("当前没有任何事件!");
			} else {
				JOptionPane.showMessageDialog(null, "进程名不能为空！");
			}
		}
	}

	private JPanel jp_main, jp_operation;
	private JLabel jl_type, jl_time, jl_size, jl_sem, jl_name;
	private JComboBox<String> jcb_type, jcb_sem;
	private JTextArea jtf_event;
	private JTextField jtf_time, jtf_size, jtf_name;
	private JButton jb_add, jb_ok;
	private List<ProcessEvent> eventslist = new ArrayList<ProcessEvent>();

	public void updateEvents() {
		jtf_event.setText("");
		for (int i = 0; i < eventslist.size(); i++) {
			ProcessEvent event = eventslist.get(i);
			if (event instanceof NormalEvent) {
				jtf_event
						.append("普通事件(NORMAL) 时间:" + ((NormalEvent) event).getTime() + " 大小:" + event.getSize() + "\n");
			} else if (event instanceof WaitEvent) {
				jtf_event.append("等待事件(WAIT)   信号量:" + ((WaitEvent) event).getSema().getName() + " 大小:"
						+ event.getSize() + "\n");
			} else if (event instanceof SignalEvent) {
				jtf_event.append("信号事件(SIGNAL)   信号量:" + ((SignalEvent) event).getSema().getName() + " 大小:"
						+ event.getSize() + "\n");
			}
		}
	}

	public void updateSemList() {
		if (jcb_sem != null)
			jcb_sem.removeAllItems();
		Iterator<Semaphore> it = MainInterface.semList.iterator();
		while (it.hasNext()) {
			jcb_sem.addItem(it.next().getName());
		}
	}

	private AddProcessInterface() {
		jp_main = new JPanel(new GridLayout(2, 1));
		jtf_event = new JTextArea("当前没有任何事件!");

		jl_type = new JLabel("事件类型");

		jcb_type = new JComboBox<String>();
		jcb_type.addItem("普通事件(Normal)");
		jcb_type.addItem("等待事件(Wait)");
		jcb_type.addItem("信号事件(Signal)");
		jcb_type.addActionListener(this);

		jl_time = new JLabel("事件时间");

		jtf_time = new JTextField();
		jtf_time.addActionListener(this);

		jl_size = new JLabel("事件内存大小");

		jtf_size = new JTextField();
		jtf_size.addActionListener(this);

		jl_sem = new JLabel("信号量");

		jcb_sem = new JComboBox<String>();
		Iterator<Semaphore> it = MainInterface.semList.iterator();
		while (it.hasNext()) {
			jcb_sem.addItem(it.next().getName());
		}
		jcb_sem.addActionListener(this);

		jl_name = new JLabel("进程名");

		jtf_name = new JTextField();
		jtf_name.addActionListener(this);

		jb_add = new JButton("添加");
		jb_add.addActionListener(this);

		jb_ok = new JButton("确定");
		jb_ok.addActionListener(this);

		jp_operation = new JPanel(new GridLayout(6, 2));
		jcb_sem.setEnabled(false);
		jtf_time.setEnabled(true);
		jtf_size.setEnabled(true);
		jp_operation.add(jl_type);
		jp_operation.add(jcb_type);
		jp_operation.add(jl_time);
		jp_operation.add(jtf_time);
		jp_operation.add(jl_size);
		jp_operation.add(jtf_size);
		jp_operation.add(jl_sem);
		jp_operation.add(jcb_sem);
		jp_operation.add(jl_name);
		jp_operation.add(jtf_name);
		jp_operation.add(jb_add);
		jp_operation.add(jb_ok);

		jp_main.add(new JScrollPane(jtf_event));
		jp_main.add(jp_operation);
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
