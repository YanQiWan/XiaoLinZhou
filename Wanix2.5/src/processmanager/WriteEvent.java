package processmanager;

import java.util.Scanner;

import javax.swing.JOptionPane;

import filemanager.Disk;
import filemanager.Document;
import userinterface.IODialog;

public class WriteEvent extends FileEvent {

	Document doc;

	public WriteEvent(Document doc) {
		this.doc = doc;

		this.size = ProcessEvent.WRITESIZE;
	}

	@Override
	public void handleEvent() throws InterruptedException {
		if (doc.getInode().getP()==p) {
//			System.out.println("Oops, I/O event in " + p.pcb.getName() + " ID: " + p.pcb.getId()
//					+ " just happened, please input the key...");
//			try {
//				ProcessController.getProcessController().block();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			new Thread(new Runnable() {
//				public void run() {
//					@SuppressWarnings("resource")
//					Scanner s = new Scanner(System.in);
//					fr.write(p, (Document) root.files.get(doc), s.nextLine());
//					System.out.println("I/O event in " + p.pcb.getName() + " has been completed.");
//					ProcessController.wake(p.pcb.getId());
//				}
//			}).start();
			fr.write(p, doc, JOptionPane.showInputDialog(null,"请输入您所要修改的内容：",doc.getContent()));
			
		}

	}

}
