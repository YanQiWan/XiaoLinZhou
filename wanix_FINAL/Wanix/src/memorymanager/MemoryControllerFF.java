package memorymanager;

import java.util.*;
import processmanager.Process;

public class MemoryControllerFF extends MemoryController {
	private static MemoryControllerFF memoryControllerFF = null;

	private MemoryControllerFF() {
		super();
	}

	public static MemoryControllerFF getInstance() {
		if (memoryControllerFF == null)
			memoryControllerFF = new MemoryControllerFF();
		return memoryControllerFF;
	}

	public boolean allocation(Process p) {
		long psize = p.pcb.getSize();
		ListIterator<PartitionItem> it = partition_table.listIterator();
		while (it.hasNext()) {
			int index = it.nextIndex();
			PartitionItem curitem = it.next();
			if (curitem.isEmpty() && curitem.getSize() >= psize) {
				curitem.setP(p);
				remaintotal -= psize;
				long remain = curitem.getSize() - psize;
				if (remain != 0) {
					curitem.setSize(psize);
					PartitionItem newItem = new PartitionItem();
					newItem.setSize(remain);
					newItem.setStartAddress(curitem.getStartAddress() + psize);
					partition_table.add(index + 1, newItem);
				}
				return true;
			}
		}
		return super.allocation(p);
	}
}
