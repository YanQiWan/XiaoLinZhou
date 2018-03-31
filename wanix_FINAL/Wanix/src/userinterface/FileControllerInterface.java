package userinterface;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import filemanager.Directory;
import filemanager.DiskBlock;
import filemanager.Document;
import filemanager.FileController;
import filemanager.FileType;
import filemanager.Inode;
import memorymanager.PartitionItem;
import processmanager.*;
import processmanager.Process;
import filemanager.File;

@SuppressWarnings("serial")
public class FileControllerInterface extends JFrame implements ActionListener, MouseListener {

	private static FileControllerInterface fileControllerInterface = null;
	private static Directory currentDir = null;
	private static List<File> filelist = null;
	private static File selectedfile = null;
	private static JLabel jl_previous = null;
	private static JLabel jl_now = null;
	private static DiskBlock blocks[] = null;

	public static FileControllerInterface getInstance() {
		if (fileControllerInterface == null) {
			currentDir = FileController.root;
			filelist = new ArrayList<File>(FileController.root.files.values());
			blocks = FileController.getDisk().getBlocks();
			fileControllerInterface = new FileControllerInterface();
			fileControllerInterface.setVisible(false);
		}
		return fileControllerInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jb[0]) {// �����ļ�
			String docname = JOptionPane.showInputDialog("�������ļ���:");
			if (docname == null)
				return;
			Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
			Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE, null,
					possibleValues, possibleValues[0]);
			if (group == null)
				return;
			String processname = JOptionPane.showInputDialog("�����������:");
			if (processname == null)
				return;
			Group g = null;
			if (group.equals(possibleValues[0])) {
				g = Group.OWNER;
			} else if (group.equals(possibleValues[1])) {
				g = Group.GROUP;
			} else {
				g = Group.OTHERS;
			}
			FileEvent fe = new TouchEvent(docname, currentDir);
			ProcessController.getProcessController()
					.create(new Process(new PCB(processname, g, FileController.root), fe));
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
				if (group == null)
					return;
				String processname = JOptionPane.showInputDialog("�����������:");
				if (processname == null)
					return;
				Group g = null;
				if (group.equals(possibleValues[0])) {
					g = Group.OWNER;
				} else if (group.equals(possibleValues[1])) {
					g = Group.GROUP;
				} else {
					g = Group.OTHERS;
				}
				ProcessController.getProcessController().create(new Process(new PCB(processname, g, currentDir), fe));
			} else {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��ѡ�е���Ŀ¼�������ô˷�ʽɾ��", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			}

		} else if (e.getSource() == jb[2]) {// ����Ŀ¼
			String dirname = JOptionPane.showInputDialog("������Ŀ¼��:");
			if (dirname == null)
				return;
			Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
			Object group = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE, null,
					possibleValues, possibleValues[0]);
			if (group == null)
				return;
			String processname = JOptionPane.showInputDialog("�����������:");
			if (processname == null)
				return;
			Group g = null;
			if (group.equals(possibleValues[0])) {
				g = Group.OWNER;
			} else if (group.equals(possibleValues[1])) {
				g = Group.GROUP;
			} else {
				g = Group.OTHERS;
			}
			FileEvent fe = new MkdirEvent(dirname, currentDir);
			ProcessController.getProcessController()
					.create(new Process(new PCB(processname, g, FileController.root), fe));
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
				if (group == null)
					return;
				String processname = JOptionPane.showInputDialog("�����������:");
				if (processname == null)
					return;
				Group g = null;
				if (group.equals(possibleValues[0])) {
					g = Group.OWNER;
				} else if (group.equals(possibleValues[1])) {
					g = Group.GROUP;
				} else {
					g = Group.OTHERS;
				}
				ProcessController.getProcessController().create(new Process(new PCB(processname, g, currentDir), fe));
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
				if (group == null)
					return;
				String processname = JOptionPane.showInputDialog("�����������:");
				if (processname == null)
					return;
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
				ProcessController.getProcessController()
						.create(new Process(new PCB(processname, g, currentDir), eventslist));
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
				if (group == null)
					return;
				String processname = JOptionPane.showInputDialog("�����������:");
				if (processname == null)
					return;
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
				ProcessController.getProcessController()
						.create(new Process(new PCB(processname, g, currentDir), eventslist));
			} else {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��ѡ�е���Ŀ¼�����ɶ�ȡ", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
			}
		} else if (e.getSource() == jb[6]) {// ����Ȩ��
			if (selectedfile == null) {
				Object[] obj = { "OK" };
				JOptionPane.showOptionDialog(null, "��δѡ���κ��ļ�", "����", JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE, null, obj, obj[0]);
				return;
			}
			Object[] possibleValues = { "OWNER", "GROUP", "OTHERS" };
			Object processgroup = JOptionPane.showInputDialog(null, "��ѡ�������", "Input", JOptionPane.INFORMATION_MESSAGE,
					null, possibleValues, possibleValues[0]);
			if (processgroup == null)
				return;
			Object filegroup = JOptionPane.showInputDialog(null, "��ѡ���޸ĺ��Ȩ��", "Input", JOptionPane.INFORMATION_MESSAGE,
					null, possibleValues, possibleValues[0]);
			if (filegroup == null)
				return;
			String processname = JOptionPane.showInputDialog("�����������:");
			if (processname == null)
				return;
			Group pg = null, fg = null;
			if (processgroup.equals(possibleValues[0])) {
				pg = Group.OWNER;
			} else if (processgroup.equals(possibleValues[1])) {
				pg = Group.GROUP;
			} else {
				pg = Group.OTHERS;
			}
			if (filegroup.equals(possibleValues[0])) {
				fg = Group.OWNER;
			} else if (filegroup.equals(possibleValues[1])) {
				fg = Group.GROUP;
			} else {
				fg = Group.OTHERS;
			}
			FileEvent fe = new ChmodEvent(selectedfile, fg);
			ProcessController.getProcessController()
					.create(new Process(new PCB(processname, pg, FileController.root), fe));
		} else if (e.getSource() == jb[7]) {// ������һ��
			currentDir = currentDir.getInode().getParent();
		}
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		updateFileControllerInterface();
		updateBlockList();
	}

	private JPanel jp_main, jp_filelist, jp_buttonlist;
	private JLabel[] jl_filelist;
	private JTable jt_blocks;
	private JButton jb[] = { new JButton("�����ļ�"), new JButton("ɾ���ļ�"), new JButton("����Ŀ¼"), new JButton("ɾ��Ŀ¼"),
			new JButton("��ȡ�ļ�"), new JButton("�޸��ļ�"), new JButton("����Ȩ��"), new JButton("������һ��") };

	public void updateFileControllerInterface() {
		filelist = new ArrayList<File>(currentDir.files.values());

		for (int i = 0; i < filelist.size(); i++) {
			Inode inode = filelist.get(i).getInode();
			if (inode.getType().compareTo(FileType.DIRECTORY) == 0)
				jl_filelist[i].setText("Dir " + inode.getPermission() + " " + inode.getFilename());
			else
				jl_filelist[i].setText("Doc " + inode.getPermission() + " " + inode.getFilename());
		}
		for (int i = filelist.size(); i < 50; i++)
			jl_filelist[i].setText("");
	}

	public void updateBlockList() {
		String columnNames[] = { "ID", "ռ���ļ�" };
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		defaultTableModel.setColumnIdentifiers(columnNames);
		for (int i = 0; i < blocks.length; i++) {
			Object data[] = new Object[2];
			DiskBlock d = blocks[i];
			data[0] = d.getId();
			if (d.f == null)
				data[1] = "EMPTY";
			else
				data[1] = d.f.getInode().getFilename();
			defaultTableModel.addRow(data);
		}
		jt_blocks.setModel(defaultTableModel);
	}

	private FileControllerInterface() {
		jt_blocks = new JTable();
		jp_filelist = new JPanel(new GridLayout(50, 1));
		jp_buttonlist = new JPanel(new GridLayout(4, 2));

		jl_filelist = new JLabel[50];
		for (int i = 0; i < 50; i++) {
			jl_filelist[i] = new JLabel();
			jl_filelist[i].addMouseListener(this);
			jp_filelist.add(jl_filelist[i]);
		}

		updateFileControllerInterface();
		updateBlockList();
		for (int i = 0; i < jb.length; i++) {
			jb[i].addActionListener(this);
			jp_buttonlist.add(jb[i]);
		}

		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// ����table���ݾ���
		// tcr.setHorizontalAlignment(JLabel.CENTER);
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// �����Ͼ�����һ��
		jt_blocks.setDefaultRenderer(Object.class, tcr);

		jp_main = new JPanel(new GridLayout(3, 1));
		jp_main.add(new JScrollPane(jp_filelist));
		jp_main.add(new JScrollPane(jt_blocks));
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
				if (file.getInode().getFilename().equals(((JLabel) e.getSource()).getText().split(" ")[2])
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
					jl_now = (JLabel) e.getSource();
					jl_now.setForeground(Color.red);
					if (jl_previous != null)
						jl_previous.setForeground(Color.BLACK);
					jl_previous = jl_now;
					break;
				}
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

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
