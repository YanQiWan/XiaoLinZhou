package processmanager;

public abstract class ProcessEvent implements Cloneable {
	protected Process p;
	protected long size;
	protected static final long WAITSIZE = 30;
	protected static final long SIGNALSIZE = 20;
	protected static final long OPENSIZE = 50;
	protected static final long CLOSESIZE = 50;
	protected static final long TOUCHSIZE = 60;
	protected static final long REMOVESIZE = 60;
	protected static final long MKDIRSIZE = 55;
	protected static final long RMDIRSIZE = 55;
	protected static final long READSIZE = 80;
	protected static final long WRITESIZE = 40;
	protected static final long CHMODSIZE = 30;

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setProcess(Process p) {
		this.p = p;
	}

	public ProcessEvent clone() {
		ProcessEvent pe = null;
		try {
			pe = (ProcessEvent) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return pe;
	}

	abstract void handleEvent() throws Exception;
}
