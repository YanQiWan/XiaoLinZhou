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
						JOptionPane.showMessageDialog(null, "�¼�ʱ�����Ϊ����");
					} else if (size <= 0 || jtf_size.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "�¼��ڴ��С����Ϊ����");
					}
				}
			} else if (jcb_type.getSelectedIndex() == 1) {
				if (flag = (!MainInterface.semList.isEmpty())) {
					eventslist.add(new WaitEvent(MainInterface.semList.get(jcb_sem.getSelectedIndex())));
				} else {
					JOptionPane.showMessageDialog(null, "�ź����б�Ϊ�գ�");
				}
			} else if (jcb_type.getSelectedIndex() == 2) {
				if (flag = (!MainInterface.semList.isEmpty())) {
					eventslist.add(new SignalEvent(MainInterface.semList.get(jcb_sem.getSelectedIndex())));
				} else {
					JOptionPane.showMessageDialog(null, "�ź����б�Ϊ�գ�");
				}
			}
			if (flag)
				updateEvents();
		} else if (e.getSource() == jb_ok) {
			if (!jtf_name.getText().isEmpty()) {
				PCB pcb = new PCB(jtf_name.getText(), Group.OWNER, FileController.root);
				ProcessController.getProcessController().create(new Process(pcb, eventslist));
				eventslist = new ArrayList<ProcessEvent>();
				jtf_event.setText("��ǰû���κ��¼�!");
			} else {
				JOptionPane.showMessageDialog(null, "����������Ϊ�գ�");
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
						.append("��ͨ�¼�(NORMAL) ʱ��:" + ((NormalEvent) event).getTime() + " ��С:" + event.getSize() + "\n");
			} else if (event instanceof WaitEvent) {
				jtf_event.append("�ȴ��¼�(WAIT)   �ź���:" + ((WaitEvent) event).getSema().getName() + " ��С:"
						+ event.getSize() + "\n");
			} else if (event instanceof SignalEvent) {
				jtf_event.append("�ź��¼�(SIGNAL)   �ź���:" + ((SignalEvent) event).getSema().getName() + " ��С:"
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
		jtf_event = new JTextArea("��ǰû���κ��¼�!");

		jl_type = new JLabel("�¼�����");

		jcb_type = new JComboBox<String>();
		jcb_type.addItem("��ͨ�¼�(Normal)");
		jcb_type.addItem("�ȴ��¼�(Wait)");
		jcb_type.addItem("�ź��¼�(Signal)");
		jcb_type.addActionListener(this);

		jl_time = new JLabel("�¼�ʱ��");

		jtf_time = new JTextField();
		jtf_time.addActionListener(this);

		jl_size = new JLabel("�¼��ڴ��С");

		jtf_size = new JTextField();
		jtf_size.addActionListener(this);

		jl_sem = new JLabel("�ź���");

		jcb_sem = new JComboBox<String>();
		Iterator<Semaphore> it = MainInterface.semList.iterator();
		while (it.hasNext()) {
			jcb_sem.addItem(it.next().getName());
		}
		jcb_sem.addActionListener(this);

		jl_name = new JLabel("������");

		jtf_name = new JTextField();
		jtf_name.addActionListener(this);

		jb_add = new JButton("���");
		jb_add.addActionListener(this);

		jb_ok = new JButton("ȷ��");
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
