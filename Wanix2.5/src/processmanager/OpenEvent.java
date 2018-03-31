package processmanager;

import filemanager.Disk;
import filemanager.Document;

public class OpenEvent extends FileEvent {

	Document doc;

	public OpenEvent(Document doc) {
		this.doc = doc;

		this.size = ProcessEvent.OPENSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.open(p,doc);
	}
}
