package userinterface;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import filemanager.Directory;
import filemanager.Document;
import filemanager.FileController;
import filemanager.FileType;
import filemanager.Inode;
import processmanager.*;
import processmanager.Process;
import filemanager.File;

@SuppressWarnings("serial")
public class FileControllerInterface extends JFrame implements ActionListener, MouseListener {

	private static FileControllerInterface fileControllerInterface = null;
	private static Directory currentDir = null;
	private static List<File> filelist = null;
	private static File selectedfile = null;

	public static FileControllerInterface getInstance() {
		if (fileControllerInterface == null) {
			currentDir = FileController.root;
			filelist = new ArrayList<File>(FileController.root.files.values());
			fileControllerInterface = new FileControllerInterface();
			fileControllerInterface.setVisible(false);
		}
		return fileControllerInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb[0]) {// �����ļ�
			FileEvent fe = new TouchEvent(JOptionPane.showInputDialog("�������ļ���:"), currentDir);
			Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
			Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE, null,
					possibleValues, possibleValues[0]);
			Group g = null;
			if (group.equals(possibleValues[0])) {
				g = Group.OWNER;
			} else if (group.equals(possibleValues[1])) {
				g = Group.GROUP;
			} else {
				g = Group.OTHERS;
			}
			ProcessController.getProcessController()
					.create(new Process(new PCB(JOptionPane.showInputDialog("�����������:"), g, FileController.root), fe));
		} else if (e.getSource() == jb[1]) {// ɾ���ļ�
			if (selectedfile == null) {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��δѡ���κ��ļ�", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			} else if (selectedfile instanceof Document) {
				FileEvent fe = new RemoveEvent((Document) selectedfile);
				Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
				Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE,
						null, possibleValues, possibleValues[0]);
				Group g = null;
				if (group.equals(possibleValues[0])) {
					g = Group.OWNER;
				} else if (group.equals(possibleValues[1])) {
					g = Group.GROUP;
				} else {
					g = Group.OTHERS;
				}
				ProcessController.getProcessController()
						.create(new Process(new PCB(JOptionPane.showInputDialog("�����������:"), g, currentDir), fe));
			} else {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��ѡ�е���Ŀ¼�������ô˷�ʽɾ��", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			}

		} else if (e.getSource() == jb[2]) {// ����Ŀ¼
			FileEvent fe = new MkdirEvent(JOptionPane.showInputDialog("�������ļ�����:"), currentDir);
			Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
			Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE, null,
					possibleValues, possibleValues[0]);
			Group g = null;
			if (group.equals(possibleValues[0])) {
				g = Group.OWNER;
			} else if (group.equals(possibleValues[1])) {
				g = Group.GROUP;
			} else {
				g = Group.OTHERS;
			}
			ProcessController.getProcessController()
					.create(new Process(new PCB(JOptionPane.showInputDialog("�����������:"), g, FileController.root), fe));
		} else if (e.getSource() == jb[3]) {// ɾ��Ŀ¼
			if (selectedfile == null) {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��δѡ���κ�Ŀ¼", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			} else if (selectedfile instanceof Directory) {
				FileEvent fe = new RmdirEvent((Directory) selectedfile);
				Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
				Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE,
						null, possibleValues, possibleValues[0]);
				Group g = null;
				if (group.equals(possibleValues[0])) {
					g = Group.OWNER;
				} else if (group.equals(possibleValues[1])) {
					g = Group.GROUP;
				} else {
					g = Group.OTHERS;
				}
				ProcessController.getProcessController()
						.create(new Process(new PCB(JOptionPane.showInputDialog("�����������:"), g, currentDir), fe));
			} else {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��ѡ�е����ļ��������ô˷�ʽɾ��", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			}
		} else if (e.getSource() == jb[4]) {// ��ȡ�ļ�
			if (selectedfile == null) {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��δѡ���κ��ļ�", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			} else if (selectedfile instanceof Document) {
				FileEvent fe = new ReadEvent((Document) selectedfile);
				Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
				Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE,
						null, possibleValues, possibleValues[0]);
				Group g = null;
				if (group.equals(possibleValues[0])) {
					g = Group.OWNER;
				} else if (group.equals(possibleValues[1])) {
					g = Group.GROUP;
				} else {
					g = Group.OTHERS;
				}
				List<ProcessEvent> eventslist = new ArrayList<ProcessEvent>();
				eventslist.add(new OpenEvent((Document) selectedfile));
				eventslist.add(fe);
				eventslist.add(new CloseEvent((Document) selectedfile));
				ProcessController.getProcessController().create(
						new Process(new PCB(JOptionPane.showInputDialog("�����������:"), g, currentDir), eventslist));
			} else {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��ѡ�е���Ŀ¼�����ɶ�ȡ", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			}
		} else if (e.getSource() == jb[5]) {// �޸��ļ�
			if (selectedfile == null) {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��δѡ���κ��ļ�", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			} else if (selectedfile instanceof Document) {
				FileEvent fe = new WriteEvent((Document) selectedfile);
				Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
				Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE,
						null, possibleValues, possibleValues[0]);
				Group g = null;
				if (group.equals(possibleValues[0])) {
					g = Group.OWNER;
				} else if (group.equals(possibleValues[1])) {
					g = Group.GROUP;
				} else {
					g = Group.OTHERS;
				}
				List<ProcessEvent> eventslist = new ArrayList<ProcessEvent>();
				eventslist.add(new OpenEvent((Document) selectedfile));
				eventslist.add(fe);
				eventslist.add(new CloseEvent((Document) selectedfile));
				ProcessController.getProcessController().create(
						new Process(new PCB(JOptionPane.showInputDialog("�����������:"), g, currentDir), eventslist));
			} else {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��ѡ�е���Ŀ¼�����ɶ�ȡ", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			}
		} else if (e.getSource() == jb[6]) {// ����Ȩ��
			Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
			Object processgroup = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE,
					null, possibleValues, possibleValues[0]);
			Group pg = null, fg = null;
			if (processgroup.equals(possibleValues[0])) {
				pg = Group.OWNER;
			} else if (processgroup.equals(possibleValues[1])) {
				pg = Group.GROUP;
			} else {
				pg = Group.OTHERS;
			}
			Object filegroup = JOptionPane.showInputDialog(null, "��ѡ���޸ĺ��Ȩ��", "Input", JOptionPane.INFORMATION_MESSAGE,
					null, possibleValues, possibleValues[0]);
			if (filegroup.equals(possibleValues[0])) {
				fg = Group.OWNER;
			} else if (filegroup.equals(possibleValues[1])) {
				fg = Group.GROUP;
			} else {
				fg = Group.OTHERS;
			}
			FileEvent fe = new ChmodEvent(selectedfile, fg);
			ProcessController.getProcessController()
					.create(new Process(new PCB(JOptionPane.showInputDialog("�����������:"), pg, FileController.root), fe));
		} else if (e.getSource() == jb[7]) {// ������һ��
			currentDir = currentDir.getInode().getParent();
		}
		try {
			Thread.sleep(20);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		updateFileControllerInterface();
	}

	private JPanel jp_main, jp_filelist, jp_blocklist, jp_buttonlist;
	private JLabel[] jl_filelist;
	private JButton jb[] = { new JButton("�����ļ�"), new JButton("ɾ���ļ�"), new JButton("����Ŀ¼"), new JButton("ɾ��Ŀ¼"),
			new JButton("��ȡ�ļ�"), new JButton("�޸��ļ�"), new JButton("����Ȩ��"), new JButton("������һ��") };

	public void updateFileControllerInterface() {
		filelist = new ArrayList<File>(currentDir.files.values());

		for (int i = 0; i < filelist.size(); i++) {
			Inode inode = filelist.get(i).getInode();
			if (inode.getType().compareTo(FileType.DIRECTORY) == 0)
				jl_filelist[i].setText("Dir " + inode.getPermission()+" "+inode.getFilename());
			else
				jl_filelist[i].setText("Doc " + inode.getPermission()+" "+inode.getFilename());
		}
		for (int i = filelist.size(); i < 50; i++)
			jl_filelist[i].setText("");
	}

	private FileControllerInterface() {

		jp_filelist = new JPanel(new GridLayout(50, 1));
		jp_buttonlist = new JPanel(new GridLayout(4, 2));

		jl_filelist = new JLabel[50];
		for (int i = 0; i < 50; i++) {
			jl_filelist[i] = new JLabel();
			jl_filelist[i].addMouseListener(this);
			jp_filelist.add(jl_filelist[i]);
		}

		updateFileControllerInterface();

		for (int i = 0; i < jb.length; i++) {
			jb[i].addActionListener(this);
			jp_buttonlist.add(jb[i]);
		}

		jp_main = new JPanel(new GridLayout(2, 1));
		jp_main.add(new JScrollPane(jp_filelist));
		jp_main.add(jp_buttonlist);

		this.add(jp_main);
		this.setSize(400, 520);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setTitle("�ļ�������");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (e.getClickCount() == 2) {
			for (int i = 0; i < filelist.size(); i++) {
				File file = filelist.get(i);
				String text=((JLabel) e.getSource()).getText().split(" ")[2];
				System.out.println(text);
				if (file.getInode().getFilename()
						.equals(((JLabel) e.getSource()).getText().split(" ")[2])
						&& file.getInode().getType().compareTo(FileType.DIRECTORY) == 0) {
					currentDir = (Directory) file;
					updateFileControllerInterface();
					break;

				}
			}
		} else if (e.getClickCount() == 1) {
			for (int i = 0; i < filelist.size(); i++) {
				File file = filelist.get(i);
				if (file.getInode().getFilename().equals(((JLabel) e.getSource()).getText().split(" ")[2])) {
					selectedfile = file;
					break;
				}
			}
		}
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
