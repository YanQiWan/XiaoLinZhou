
package processmanager;

public class WaitEvent extends ProcessEvent {
	private Semaphore sema;

	public Semaphore getSema() {
		return sema;
	}

	public WaitEvent(Semaphore sema) {
		super();
		this.sema = sema;
		this.size = ProcessEvent.WAITSIZE;
	}

	void handleEvent() throws Exception {
		sema.P(p);
	}

}
