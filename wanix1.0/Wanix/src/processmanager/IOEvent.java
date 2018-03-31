package processmanager;

import java.util.Scanner;

public class IOEvent extends ProcessEvent {

	public IOEvent() {
		super();
		size = ProcessEvent.IOSIZE;
	}
	
	private String condition = "";

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	@Override
	public void handleEvent() {
		System.out.println("Oops, I/O event in " + p.pcb.getName() + " ID: " + p.pcb.getId()+ " just happened, please input the key...");
		try {
			ProcessController.block();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new Thread(new Runnable(){
			public void run() {
				Scanner s = new Scanner(System.in);
				condition = s.next();
				String keystr = new Long(p.pcb.getId()).toString();
				while (!condition.equals(keystr)) {
					condition = s.next();
				}
				System.out.println("I/O event in " + p.pcb.getName() + " has been completed.");
				ProcessController.wake(p.pcb.getId());
			}
		}).start();
	}

}
