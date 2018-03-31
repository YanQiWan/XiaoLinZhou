package processmanager;

import java.util.*;

public class Process {
	public PCB pcb;
	List<ProcessEvent> events = new ArrayList<ProcessEvent>();

	public Process(PCB pcb, List<ProcessEvent> events) {
		super();
		long size = 0;
		this.pcb = pcb;
		this.events = events;
		Iterator<ProcessEvent> it = events.iterator();
		ProcessEvent pe;
		while (it.hasNext()) {
			pe=it.next();
			pe.setProcess(this);
			size+=pe.getSize();
		}
		this.pcb.setSize(size);
	}

	Process(PCB pcb) {
		this.pcb = pcb;
	}

	public void run() throws Exception {
		if (!events.isEmpty()) {
			try {
				events.get(0).handleEvent();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!(events.get(0) instanceof NormalEvent && ((NormalEvent) events.get(0)).getTime() > 0)) {
				events.remove(0);
			}
		} 
		if (events.isEmpty()){
			ProcessController.remove();
			System.out.println("Process " + pcb.getName() + " is terminated.");
			System.out.println("Process " + pcb.getName() + "'s size is "+pcb.getSize());
		}
	}

}
