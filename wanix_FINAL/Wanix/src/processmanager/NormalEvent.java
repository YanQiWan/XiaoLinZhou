package processmanager;

import userinterface.ProcessControllerInterface;

public class NormalEvent extends ProcessEvent {
	private long time;// ����ʱ��

	public long getTime() {
		return time;
	}

	@SuppressWarnings("static-access")
	@Override
	void handleEvent() throws Exception {
		long t = ProcessController.getProcessController().getTimeSlice();
		if (time < t) {
			// ProcessControler.currentTime+=time;
			p.pcb.setExecutedTime(p.pcb.getExecutedTime() + time);
			ProcessControllerInterface.getInstance()
					.updateProcessControllerLog("Process " + p.pcb.getName() + " running " + time + " seconds...");
			Thread.sleep(time * 1000);
			time = 0;
		} else {
			p.pcb.setExecutedTime(p.pcb.getExecutedTime() + t);
			ProcessControllerInterface.getInstance()
					.updateProcessControllerLog("Process " + p.pcb.getName() + " running " + t + " seconds...");
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
