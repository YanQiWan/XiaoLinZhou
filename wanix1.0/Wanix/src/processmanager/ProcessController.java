package processmanager;

import java.util.*;

import memorymanager.MemoryControllerFF;

public class ProcessController implements Runnable {
	public static List<Process> block_queue = new ArrayList<Process>();
	public static List<Process> ready_queue = new ArrayList<Process>();
	public static Process runningProcess = null;
	public static long currentTime = 0;
	public static long timeSlice = 1;
	
	public static boolean create(Process proc) {
		boolean flag;
		if(flag=MemoryControllerFF.allocation(proc))
			ready_queue.add(proc);
		MemoryControllerFF.display();
		return flag;
	}

	public static boolean ready() throws Exception {
		if (runningProcess != null) {
			runningProcess.pcb.setState(PCB.READY);
			ProcessController.ready_queue.add(runningProcess);
			runningProcess = null;
		}
		while (ready_queue.size() == 0 && runningProcess == null) {
			Thread.sleep(1);
		}
		runningProcess = ready_queue.get(0);
		ready_queue.remove(0);
		runningProcess.pcb.setState(PCB.RUN);
		return true;
	}

	public static boolean block() throws Exception {
		block_queue.add(runningProcess);
		runningProcess.pcb.setState(PCB.BLOCK);
		runningProcess=null;
		return true;
	}

	public static boolean remove() throws Exception {
		MemoryControllerFF.release(runningProcess);
		if (ready_queue.size() == 0) {
			runningProcess = null;
		} else {
			runningProcess = ready_queue.get(0);
			ready_queue.remove(0);
		}
		MemoryControllerFF.display();
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
			while (ready_queue.isEmpty() && runningProcess == null) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				ready();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			runningProcess.run();
		}
	}

	@Override
	public void run() {
		try {
			schedule();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
