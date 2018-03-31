package userinterface;

import java.awt.BorderLayout;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import memorymanager.PartitionItem;
import processmanager.ProcessController;

@SuppressWarnings("serial")
public class MemoryControllerInterface extends JFrame implements ActionListener, MouseListener {

	private static MemoryControllerInterface memoryControllerInterface = null;
	private static List<PartitionItem> partition_table = null;

	public static MemoryControllerInterface getInstance() {
		if (memoryControllerInterface == null) {
			partition_table = ProcessController.mr.partition_table;
			memoryControllerInterface = new MemoryControllerInterface();
			memoryControllerInterface.setVisible(false);
		}
		return memoryControllerInterface;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}

	private JPanel jp_main;
	private JTable jt_display;

	public void updateMemoryControllerInterface() {
		String columnNames[] = { "起始地址", "分区长度", "占用进程" };
		DefaultTableModel defaultTableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		defaultTableModel.setColumnIdentifiers(columnNames);
		for (int i = 0; i < partition_table.size(); i++) {
			Object data[] = new Object[3];
			PartitionItem p = partition_table.get(i);
			data[0] = p.getStartAddress();
			data[1] = p.getSize();
			if (p.isEmpty())
				data[2] = "AVAILABLE";
			else
				data[2] = p.getP().pcb.getName();
			defaultTableModel.addRow(data);
		}
		jt_display.setModel(defaultTableModel);
	}

	private MemoryControllerInterface() {
		jt_display = new JTable();
		updateMemoryControllerInterface();
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		// tcr.setHorizontalAlignment(JLabel.CENTER);
		tcr.setHorizontalAlignment(SwingConstants.CENTER);// 这句和上句作用一样
		jt_display.setDefaultRenderer(Object.class, tcr);
		jp_main = new JPanel(new BorderLayout());
		jp_main.add(new JScrollPane(jt_display), "Center");

		this.add(jp_main);
		this.setSize(400, 520);
		this.setLocation(200, 200);
		this.setResizable(false);
		this.setTitle("内存管理器");
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
