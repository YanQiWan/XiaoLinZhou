package processmanager;

public class SignalEvent extends ProcessEvent {
	Semaphore sema;

	public SignalEvent(Semaphore sema) {
		super();
		this.sema = sema;
		this.size = ProcessEvent.SIGNALSIZE;
	}

	void handleEvent() throws Exception {
		sema.V();
	}

}
