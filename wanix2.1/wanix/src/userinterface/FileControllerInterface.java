package userinterface;

import java.awt.GridLayout;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import filemanager.Directory;
import filemanager.FileController;
import filemanager.FileType;
import filemanager.Inode;
import filemanager.File;

@SuppressWarnings("serial")
public class FileControllerInterface extends JFrame implements ActionListener, MouseListener {

	private static FileControllerInterface fileControllerInterface = null;
	private static Directory currentDir=null;
	private static List<File> filelist=null;

	public static FileControllerInterface getInstance() {
		if (fileControllerInterface == null) {
			currentDir=FileController.root;
			filelist=new ArrayList<File>(FileController.root.files.values());
			fileControllerInterface = new FileControllerInterface();
			fileControllerInterface.setVisible(false);
		}
		return fileControllerInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private JPanel jp_main,jp_filelist,jp_blocklist,jp_buttonlist;
	private JLabel[] jl_filelist;
	private JButton jb[]={ new JButton("创建文件"),new JButton("删除文件"),
			new JButton("打开文件"),new JButton("关闭文件"),
			new JButton("创建目录"),new JButton("删除目录"),
			new JButton("读取文件"),new JButton("修改文件"),
			new JButton("更改权限"),new JButton("更改当前目录")};

	public void updateFileControllerInterface() {
		for(int i=0;i<filelist.size();i++)
		{
			Inode inode=filelist.get(i).getInode();
			if(inode.getType().compareTo(FileType.DIRECTORY)==0)
				jl_filelist[i].setText("Dir  "+inode.getFilename());
			else
				jl_filelist[i].setText("Doc  "+inode.getFilename());
		}
		for(int i=filelist.size();i<50;i++)
			jl_filelist[i].setText("");
	}

	private FileControllerInterface() {
		
		jp_filelist=new JPanel(new GridLayout(50, 1));
		jp_buttonlist=new JPanel(new GridLayout(5,2));
		
		jl_filelist=new JLabel[50];
		for(int i=0;i<50;i++)
		{
			jl_filelist[i]=new JLabel();
			jp_filelist.add(jl_filelist[i]);
		}
		
		updateFileControllerInterface();
		
		for(int i=0;i<jb.length;i++)
		{
			jb[i].addActionListener(this);
			jp_buttonlist.add(jb[i]);
		}
		
		jp_main = new JPanel(new GridLayout(2,1));
		jp_main.add(new JScrollPane(jp_filelist));
		jp_main.add(jp_buttonlist);

		this.add(jp_main);
		this.setSize(400, 520);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setTitle("文件管理器");
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
