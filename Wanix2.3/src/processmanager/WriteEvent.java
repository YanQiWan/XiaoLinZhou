package processmanager;

import java.util.Scanner;

import filemanager.Disk;
import filemanager.Document;

public class WriteEvent extends FileEvent {

	String doc;

	public WriteEvent(String doc) {
		this.doc = doc;

		this.size = ProcessEvent.WRITESIZE;
	}

	@Override
	public void handleEvent() {
		if (root.files.get(doc).getInode().getP().equals(p)) {
			System.out.println("Oops, I/O event in " + p.pcb.getName() + " ID: " + p.pcb.getId()
					+ " just happened, please input the key...");
			try {
				ProcessController.getProcessController().block();
			} catch (Exception e) {
				e.printStackTrace();
			}
			new Thread(new Runnable() {
				public void run() {
					@SuppressWarnings("resource")
					Scanner s = new Scanner(System.in);
					fr.write(p, (Document) root.files.get(doc), s.nextLine());
					System.out.println("I/O event in " + p.pcb.getName() + " has been completed.");
					ProcessController.wake(p.pcb.getId());
				}
			}).start();
		}

	}

}
