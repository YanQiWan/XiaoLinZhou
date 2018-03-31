package main;

import java.util.*;

import filemanager.Directory;
import filemanager.Disk;
import filemanager.Document;
import filemanager.FileController;
import processmanager.*;
import processmanager.Process;
import userinterface.MainInterface;
import userinterface.MemoryControllerInterface;

public class Main {

	public static void main(String args[]) {
//
//		/*
//		 * PCB pcbprod[] = new PCB[5], pcbcons[] = new PCB[5]; int i, j = 0; for
//		 * (i = 0; i < 5; i++) { pcbprod[i] = new PCB(j, 1, 4, "Producer " + i);
//		 * pcbcons[i] = new PCB(j + 1, 1, 4, "Consumer " + i); j += 2; }
//		 * 
//		 * List<ProcessEvent> prodevents = new ArrayList<ProcessEvent>();
//		 * List<ProcessEvent> consevents = new ArrayList<ProcessEvent>();
//		 * 
//		 * Semaphore mutex = new Semaphore("mutex", 1); Semaphore empty = new
//		 * Semaphore("empty", 2); Semaphore full = new Semaphore("full", 0);
//		 * 
//		 * prodevents.add(new NormalEvent(2, 100)); prodevents.add(new
//		 * WriteEvent()); prodevents.add(new WaitEvent(empty));
//		 * prodevents.add(new WaitEvent(mutex)); prodevents.add(new
//		 * NormalEvent(2, 50)); prodevents.add(new SignalEvent(mutex));
//		 * prodevents.add(new SignalEvent(full));
//		 * 
//		 * consevents.add(new WaitEvent(full)); consevents.add(new
//		 * WaitEvent(mutex)); consevents.add(new NormalEvent(2, 50));
//		 * consevents.add(new WriteEvent()); consevents.add(new
//		 * SignalEvent(mutex)); consevents.add(new SignalEvent(empty));
//		 * consevents.add(new NormalEvent(2, 80));
//		 * 
//		 * List<ProcessEvent> tempevents;
//		 * 
//		 * for (i = 0; i < 2; i++) { tempevents = new ArrayList<ProcessEvent>();
//		 * Iterator<ProcessEvent> it = prodevents.iterator(); while
//		 * (it.hasNext()) { tempevents.add(it.next().clone()); }
//		 * ProcessController.getProcessController().create(new
//		 * Process(pcbprod[i], tempevents)); tempevents = new
//		 * ArrayList<ProcessEvent>(); it = consevents.iterator(); while
//		 * (it.hasNext()) { tempevents.add(it.next().clone()); }
//		 * ProcessController.getProcessController().create(new
//		 * Process(pcbcons[i], tempevents)); }
//		 */
//		FileController fr = FileController.getFileController();
//		ProcessController pc = ProcessController.getProcessController();
//		Directory root = FileController.root;
//		/*PCB pcb1 = new PCB(0, 1, 4, "ProcessOwner", Group.OWNER, root);
//		PCB pcb2 = new PCB(1, 1, 4, "ProcessGroup", Group.GROUP, root);
//		PCB pcb3 = new PCB(2, 1, 4, "ProcessOthers", Group.OTHERS,root);*/
//
//		List<ProcessEvent> events1 = new ArrayList<ProcessEvent>();
//		List<ProcessEvent> events2 = new ArrayList<ProcessEvent>();
//		List<ProcessEvent> events3 = new ArrayList<ProcessEvent>();
//		
//		events1.add(new TouchEvent("DOC_OWNER",root));
//
//		events2.add(new TouchEvent("DOC_GROUP",root));
//		
//		events3.add(new TouchEvent("DOC_OTHERS",root));
//		
//		events1.add(new MkdirEvent("DIR_OWNER",root));
//		
//		Process p1 = new Process(pcb1, events1);
//		Process p2 = new Process(pcb2, events2);
//		Process p3 = new Process(pcb3, events3);
//
//		pc.create(p1);
//		pc.create(p2);
//		pc.create(p3);
//
////		new Thread(pc).start();
//		MainInterface.getInstance();
	}
}
