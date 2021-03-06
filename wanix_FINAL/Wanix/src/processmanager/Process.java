package processmanager;

import java.util.*;

import userinterface.ProcessControllerInterface;

public class Process {
	public PCB pcb;
	List<ProcessEvent> events = new ArrayList<ProcessEvent>();

	public Process(PCB pcb, List<ProcessEvent> events) {
		super();
		long size = 0;
		this.pcb = pcb;
		this.events = events;
		if (events != null) {
			Iterator<ProcessEvent> it = events.iterator();
			ProcessEvent pe;
			while (it.hasNext()) {
				pe = it.next();
				pe.setProcess(this);
				size += pe.getSize();
			}
		}
		this.pcb.setSize(size);
	}

	public Process(PCB pcb, ProcessEvent e) {
		this.pcb = pcb;
		events.add(e);
		e.setProcess(this);
		this.pcb.setSize(e.getSize());
	}

	public void run() throws Exception {
		if (!events.isEmpty()) {
			events.get(0).handleEvent();
			if (!(events.get(0) instanceof NormalEvent && ((NormalEvent) events.get(0)).getTime() > 0)) {
				events.remove(0);
			}
		}
		if (events.isEmpty()) {
			ProcessController.getProcessController().remove();
			ProcessControllerInterface.getInstance()
					.updateProcessControllerLog("Process " + pcb.getName() + " is terminated.");
		}
	}

}
