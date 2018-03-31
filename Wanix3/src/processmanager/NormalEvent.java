package processmanager;

public class NormalEvent extends ProcessEvent {
	private long time;// 运行时间

	public long getTime() {
		return time;
	}

	@SuppressWarnings("static-access")
	@Override
	void handleEvent() throws Exception {
		long t = ProcessController.getProcessController().getTimeSlice();
		if (time < t) {
			System.out.println("Process " + p.pcb.getName() + " running " + time + " seconds...");
			Thread.sleep(time * 1000);
			time = 0;
		} else {
			System.out.println("Process " + p.pcb.getName() + " running " + t + " seconds...");
			time -= t;
			Thread.sleep(t * 1000);
			// ProcessControler.ready();
		}
	}

	public NormalEvent(long time, long size) {
		super();
		this.time = time;
		this.size = size;
	}
}
