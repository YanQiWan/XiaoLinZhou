package processmanager;

import filemanager.Disk;
import filemanager.Document;

public class OpenEvent extends FileEvent {

	String doc;

	public OpenEvent(String doc) {
		this.doc = doc;

		this.size = ProcessEvent.OPENSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.open(p,(Document) root.files.get(doc));
	}
}
