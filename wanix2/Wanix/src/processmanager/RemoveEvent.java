package processmanager;

import filemanager.Disk;
import filemanager.Document;

public class RemoveEvent extends FileEvent {
	String doc;

	public RemoveEvent(String doc) {
		this.doc = doc;
		this.size = ProcessEvent.REMOVESIZE;
	}

	void handleEvent() throws Exception {
		fr.remove(p, (Document)root.files.get(doc));
		fr.display(root);
	}

}
