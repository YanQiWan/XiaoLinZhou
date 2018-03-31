package processmanager;

public class NormalEvent extends ProcessEvent {
	private long time;// 运行时间
	public long getTime() {
		return time;
	}
	@Override
	void handleEvent() throws Exception {
		if (time < ProcessController.timeSlice) {
			// ProcessControler.currentTime+=time;
			p.pcb.setExecutedTime(p.pcb.getExecutedTime() + time);
			System.out.println("Process " + p.pcb.getName() + " running " + time + " seconds...");
			Thread.sleep(time * 1000);
			time = 0;
		} else {
			p.pcb.setExecutedTime(p.pcb.getExecutedTime() + ProcessController.timeSlice);
			System.out.println("Process " + p.pcb.getName() + " running " + ProcessController.timeSlice + " seconds...");
			time -= ProcessController.timeSlice;
			Thread.sleep(ProcessController.timeSlice * 1000);
			// ProcessControler.ready();
		}
	}
	public NormalEvent(long time,long size) {
		super();
		this.time = time;
		this.size = size;
	}
}
