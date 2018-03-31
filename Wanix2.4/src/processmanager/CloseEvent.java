package processmanager;

import filemanager.Disk;
import filemanager.Document;

public class CloseEvent extends FileEvent {

	String doc;

	public CloseEvent(String doc) {
		this.doc = doc;
		this.size = ProcessEvent.CLOSESIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.close(p, (Document) root.files.get(doc));
	}

}
