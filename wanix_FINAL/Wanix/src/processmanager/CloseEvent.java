package processmanager;

import filemanager.Disk;
import filemanager.Document;

public class CloseEvent extends FileEvent {

	Document doc;

	public CloseEvent(Document doc) {
		this.doc = doc;
		this.size = ProcessEvent.CLOSESIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.close(p, doc);
	}

}
