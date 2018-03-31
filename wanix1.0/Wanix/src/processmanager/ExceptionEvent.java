package processmanager;

public class ExceptionEvent extends ProcessEvent {

	public ExceptionEvent() {
		super();
	}

	@Override
	public void handleEvent() {
		try {
			ProcessController.remove();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
