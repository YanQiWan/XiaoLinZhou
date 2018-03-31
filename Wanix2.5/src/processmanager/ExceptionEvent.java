package processmanager;

public class ExceptionEvent extends ProcessEvent {

	public ExceptionEvent() {
		super();
	}

	@Override
	public void handleEvent() {
		try {
			ProcessController.getProcessController().remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
