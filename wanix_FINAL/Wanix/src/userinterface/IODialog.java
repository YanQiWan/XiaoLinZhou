package userinterface;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import processmanager.Process;

@SuppressWarnings("serial")
public class IODialog extends JFrame implements ActionListener {

	private JButton jb_ensure;
	private JTextField jtf;
	private JTextArea jta_input;
	private JPanel jp_dialog;
	String input = null;
	boolean flag = true;

	public IODialog(Process p) {
		jp_dialog = new JPanel(new GridLayout(3, 1));
		jtf = new JTextField("输入信息:");
		jtf.setEditable(false);
		jta_input = new JTextArea();
		jb_ensure = new JButton("确定");
		jb_ensure.addActionListener(this);

		jp_dialog.add(jtf);
		jp_dialog.add(jta_input);
		jp_dialog.add(jb_ensure);

		this.add(jp_dialog);
		this.setSize(200, 100);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setTitle(p.pcb.getName());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static String getInputDialog(Process p) throws InterruptedException {
		IODialog iodialog = new IODialog(p);
		while (iodialog.flag == true) {
			Thread.sleep(1);
		}
		iodialog.dispose();
		return iodialog.input;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb_ensure) {
			input = jta_input.getText().trim();
			System.out.println(input);
			flag = false;
		}
	}
}
