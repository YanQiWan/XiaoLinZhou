package processmanager;

import java.util.Scanner;

import javax.swing.JOptionPane;

import filemanager.Disk;
import filemanager.Document;
import userinterface.FileControllerInterface;
import userinterface.IODialog;

public class WriteEvent extends FileEvent {

	Document doc;

	public WriteEvent(Document doc) {
		this.doc = doc;

		this.size = ProcessEvent.WRITESIZE;
	}

	@Override
	public void handleEvent() throws InterruptedException {
		if (doc.getInode().getP() == p) {
			fr.write(p, doc, JOptionPane.showInputDialog(null, "请输入您所要修改的内容：", doc.getContent()));
			FileControllerInterface.getInstance().updateBlockList();
		}

	}

}
