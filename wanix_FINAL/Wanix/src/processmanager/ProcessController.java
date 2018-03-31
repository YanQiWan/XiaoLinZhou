package processmanager;

import java.util.*;

import filemanager.File;
import memorymanager.*;
import userinterface.MemoryControllerInterface;
import userinterface.ProcessControllerInterface;

public class ProcessController implements Runnable {
	private static List<Process> block_queue = null;
	private static List<Process> ready_queue = null;
	private static Process runningProcess = null;

	private static long timeSlice;
	public static MemoryController mr = null;
	private static ProcessController processController = null;
	public static int mc_type = 1;
	public static final int MC_FF = 1;
	public static final int MC_BF = 2;

	private ProcessController() {
		block_queue = new ArrayList<Process>();
		ready_queue = new ArrayList<Process>();
		runningProcess = null;
		timeSlice = 1;
	}

	public static long getTimeSlice() {
		return timeSlice;
	}

	public static ProcessController getProcessController() {
		if (processController == null) {
			processController = new ProcessController();
			if (mc_type == MC_BF)
				mr = MemoryControllerBF.getInstance();
			else if (mc_type == MC_FF)
				mr = MemoryControllerFF.getInstance();
			new Thread(ProcessController.getProcessController()).start();
		}
		return processController;
	}

	public boolean create(Process proc) {
		boolean flag;
		if (flag = mr.allocation(proc))
			ready_queue.add(proc);
		MemoryControllerInterface.getInstance().updateMemoryControllerInterface();
		return flag;
	}

	public boolean ready() throws Exception {
		if (runningProcess != null) {
			runningProcess.pcb.setState(PCB.READY);
			ProcessController.ready_queue.add(runningProcess);
			runningProcess = null;
		}
		while (ready_queue.size() == 0) {
			Thread.sleep(1);
		}
		runningProcess = ready_queue.get(0);
		ready_queue.remove(0);
		runningProcess.pcb.setState(PCB.RUN);
		return true;
	}

	public boolean block() throws Exception {
		block_queue.add(runningProcess);
		runningProcess.pcb.setState(PCB.BLOCK);
		runningProcess = null;
		return true;
	}

	public boolean remove() throws Exception {
		for (File f : runningProcess.pcb.getFiles()) {
			f.getInode().setP(null);
		}
		mr.release(runningProcess);
		runningProcess = null;
		MemoryControllerInterface.getInstance().updateMemoryControllerInterface();
		return true;
	}

	public static boolean wake(long id) {
		Iterator<Process> it = block_queue.iterator();
		int index = 0;
		while (it.hasNext()) {
			Process cur = it.next();
			if (cur.pcb.getId() == id)
				break;
			index++;
		}
		if (index == block_queue.size()) {
			return false;
		}
		Process p = block_queue.get(index);
		block_queue.remove(index);
		ready_queue.add(p);
		p.pcb.setState(PCB.READY);
		return true;
	}

	public static void schedule() throws Exception {
		while (true) {
			ProcessController.getProcessController().ready();
			runningProcess.run();
			ProcessControllerInterface.getInstance().updateProcessControllerInterface();
		}
	}

	public static Process getRunningProcess() {
		return runningProcess;
	}

	public static List<Process> getBlock_queue() {
		return block_queue;
	}

	public static List<Process> getReady_queue() {
		return ready_queue;
	}

	@Override
	public void run() {
		try {
			schedule();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
