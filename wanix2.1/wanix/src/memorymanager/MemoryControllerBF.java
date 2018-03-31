package memorymanager;

import java.util.ListIterator;

import processmanager.Process;

public class MemoryControllerBF extends MemoryController {
	private static MemoryControllerBF memoryControllerBF=null;
	
	private MemoryControllerBF()
	{
		super();
	}
	public static MemoryControllerBF getInstance()
	{
		if(memoryControllerBF==null)
			memoryControllerBF=new MemoryControllerBF();
		return memoryControllerBF;
	}
	
	public boolean allocation(Process p) {
		long psize = p.pcb.getSize();
		ListIterator<PartitionItem> it = partition_table.listIterator();
		long difference = MAX;
		int minIndex = -1;
		while (it.hasNext()) {
			int index = 0;
			index = it.nextIndex();
			PartitionItem curitem = it.next();
			if (curitem.isEmpty() && curitem.getSize() >= psize) {
				if ((curitem.getSize() - psize) < difference) {
					minIndex = index;
					difference = (curitem.getSize() - psize);
				}
			}
		}
		if (minIndex != -1) {
			PartitionItem curitem;
			curitem = partition_table.get(minIndex);
			curitem.setP(p);
			remaintotal -= psize;
			if (difference != 0) {
				curitem.setSize(psize);
				PartitionItem newItem = new PartitionItem();
				newItem.setSize(difference);
				newItem.setStartAddress(curitem.getStartAddress() + psize);
				partition_table.add(minIndex + 1, newItem);
			}
			return true;
		}
		else return super.allocation(p);
	}
}
