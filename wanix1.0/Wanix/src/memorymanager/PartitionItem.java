package memorymanager;
import processmanager.Process;
public class PartitionItem {
	long size; 
	Process p = null;
	long startAddress;

	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public Process getP() {
		return p;
	}
	public void setP(Process p) {
		this.p = p;
	}
	public long getStartAddress() {
		return startAddress;
	}
	public void setStartAddress(long startAddress) {
		this.startAddress = startAddress;
	}
	public boolean isEmpty() {
		return (p==null);
	}
}
