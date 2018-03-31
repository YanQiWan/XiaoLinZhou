package processmanager;

public abstract class ProcessEvent implements Cloneable {
	protected Process p;
	protected long size;
	protected static final long IOSIZE = 50;
	protected static final long WAITSIZE = 30;
	protected static final long SIGNALSIZE = 20;
	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public void setProcess(Process p) {
		this.p = p;
	}

    public ProcessEvent clone(){ 
    	ProcessEvent pe = null; 
        try{ 
            pe = (ProcessEvent)super.clone(); 
        }catch(CloneNotSupportedException e){ 
            e.printStackTrace(); 
        } 
        return pe; 
    } 
	
	abstract void handleEvent() throws Exception;
}
