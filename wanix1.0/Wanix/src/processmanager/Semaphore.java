package processmanager;

import java.util.*;

public class Semaphore {
	private String name;
	private int value;// 信号量值
	private ArrayList<Process> queue=new ArrayList<Process>();// 进程阻塞队列
	
	public String getName() {
		return name;
	}
	
	public Semaphore(String name,int value) {
		this.name=name;
		if(value>=0)
			this.value=value;
	}

	public void P(Process p) throws Exception {
		value--;
		if(value<0){
			queue.add(p);
			ProcessController.block();
			System.out.println("Process "+p.pcb.getName()+
					" is blocked by semaphore "+name);
		}
		else{
			System.out.println("Process "+p.pcb.getName()+
					" went through semaphore "+name);
		}
	}

	public void V() {
		value++;
		if(value<=0){
			Process p = queue.get(0);
			queue.remove(0);
			ProcessController.wake(p.pcb.getId());
			System.out.println("Process "+p.pcb.getName()+
					" is waken up by semaphore "+name);
		}
		else{
			System.out.println("Increased semaphore "+name);
		}
	}
}
