package memorymanager;

import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

import processmanager.Process;

public abstract class MemoryController {
	public final long MEMORYSIZE = 4400;
	long remaintotal = MEMORYSIZE;
	final int MAX = 2000000;
	public List<PartitionItem> partition_table = new CopyOnWriteArrayList<PartitionItem>();

	MemoryController() {
		PartitionItem memory = new PartitionItem();
		memory.setSize(MEMORYSIZE);
		memory.setStartAddress(0);
		partition_table.add(memory);
	}

	public boolean allocation(Process p) {
		long psize = p.pcb.getSize();
		if (remaintotal >= psize) {
			ListIterator<PartitionItem> it = partition_table.listIterator();
			long empty = 0, occupied = 0;
			int index = 0;
			while (empty < psize) {
				PartitionItem curitem = it.next();
				if (curitem.isEmpty()) {
					empty += curitem.getSize();
					partition_table.remove(curitem);
				} else {
					curitem.setStartAddress(occupied);
					occupied += curitem.getSize();
					index++;
				}
			}
			PartitionItem newItem = new PartitionItem();
			newItem.setStartAddress(occupied);
			newItem.setSize(psize);
			newItem.setP(p);

			partition_table.add(index, newItem);
			if (empty != psize) {
				newItem = new PartitionItem();
				newItem.setStartAddress(occupied + psize);
				newItem.setSize(empty - psize);
				partition_table.add(index + 1, newItem);
			}
			return true;
		} else {
			System.out.println("Failed to allocate:" + p.pcb.getName());
			return false;
		}
	}

	public boolean release(Process p) {
		ListIterator<PartitionItem> it = partition_table.listIterator();
		int index = 0;
		while (it.hasNext()) {
			index = it.nextIndex();
			PartitionItem curitem = it.next();
			if (curitem.getP() == p) {
				break;
			}
		}
		PartitionItem prevItem = null;
		PartitionItem thisItem;
		PartitionItem nextItem = null;
		if (index > 0) {
			prevItem = partition_table.get(index - 1);
		}
		thisItem = partition_table.get(index);
		if (index < partition_table.size() - 1)
			nextItem = partition_table.get(index + 1);
		thisItem.setP(null);
		remaintotal += thisItem.getSize();
		if (prevItem != null && prevItem.isEmpty()) {
			thisItem.setStartAddress(prevItem.getStartAddress());
			thisItem.setSize(prevItem.getSize() + thisItem.getSize());
			partition_table.remove(prevItem);
		}
		if (nextItem != null && nextItem.isEmpty()) {
			thisItem.setSize(nextItem.getSize() + thisItem.getSize());
			partition_table.remove(nextItem);
		}
		return true;
	}

	public void display() {
		ListIterator<PartitionItem> it = partition_table.listIterator();
		System.out.println("SA\tSize\tProcess");
		while (it.hasNext()) {
			PartitionItem pi = it.next();
			if (pi.isEmpty()) {
				System.out.println(pi.getStartAddress() + "\t" + pi.getSize() + "\t" + "EMPTY");
			} else {
				System.out.println(pi.getStartAddress() + "\t" + pi.getSize() + "\t" + pi.getP().pcb.getName());
			}
		}
	}
}
