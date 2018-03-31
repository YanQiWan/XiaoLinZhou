package processmanager;

public class SignalEvent extends ProcessEvent {
	private Semaphore sema;

	public Semaphore getSema() {
		return sema;
	}

	public SignalEvent(Semaphore sema) {
		super();
		this.sema = sema;
		this.size = ProcessEvent.SIGNALSIZE;
	}

	void handleEvent() throws Exception {
		sema.V();
	}

}
