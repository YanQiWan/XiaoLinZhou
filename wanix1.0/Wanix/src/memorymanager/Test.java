package memorymanager;

import java.util.*;
import java.util.concurrent.*;

import processmanager.*;
import processmanager.Process;
import processmanager.Semaphore;


public class Test {

	public static void main(String[] args) {
		PCB pcbprod[]=new PCB[5],pcbcons[]=new PCB[5];
		int i,j=0;
		for(i=0;i<5;i++)
		{
			pcbprod[i]=new PCB(j,1,4,"Producer "+i);
			pcbcons[i]=new PCB(j+1,1,4,"Consumer "+i);
			j+=2;
		}
		
		List<ProcessEvent> prodevents=new ArrayList<ProcessEvent>();
		List<ProcessEvent> consevents=new ArrayList<ProcessEvent>();
		
		Semaphore mutex=new Semaphore("mutex",1);
		Semaphore empty=new Semaphore("empty",2);
		Semaphore full=new Semaphore("full",0);
		
		prodevents.add(new NormalEvent(2,150));
		prodevents.add(new IOEvent());
		prodevents.add(new WaitEvent(empty));
		prodevents.add(new WaitEvent(mutex));
		prodevents.add(new NormalEvent(2,300));
		prodevents.add(new SignalEvent(mutex));
		prodevents.add(new SignalEvent(full));
	
		consevents.add(new WaitEvent(full));
		consevents.add(new WaitEvent(mutex));
		consevents.add(new NormalEvent(2,100));
		consevents.add(new IOEvent());
		consevents.add(new SignalEvent(mutex));
		consevents.add(new SignalEvent(empty));
		consevents.add(new NormalEvent(2,250));
		
		List<ProcessEvent> tempevents;
		
		Process pro[],con[];
		pro=new Process[5];
		con=new Process[5];
		
		for(i=0;i<5;i++)
		{
			tempevents=new ArrayList<ProcessEvent>();
			Iterator<ProcessEvent> it = prodevents.iterator();
			while (it.hasNext()) {
				tempevents.add(it.next().clone());
			}
			pro[i]=new Process(pcbprod[i],tempevents);
			boolean flag = MemoryControllerBF.allocation(pro[i]);
			
			tempevents=new ArrayList<ProcessEvent>();
			it = consevents.iterator();
			while (it.hasNext()) {
				tempevents.add(it.next().clone());
			}
			con[i]=new Process(pcbcons[i],tempevents);
			flag = MemoryControllerBF.allocation(con[i]);
		}
//		MemoryControllerBF.release(pro[1]);
		MemoryControllerBF.release(pro[2]);

		MemoryControllerBF.release(con[1]);

//		MemoryControllerBF.release(pro[0]);

		MemoryControllerBF.display();
		
	}

}
