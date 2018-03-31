package processmanager;

import filemanager.Directory;
import filemanager.Document;

public class RemoveEvent extends FileEvent {
	Document doc;

	public RemoveEvent(Document doc) {
		this.doc = doc;
		this.size = ProcessEvent.REMOVESIZE;
	}

	void handleEvent() throws Exception {
		fr.remove(p, doc);
		fr.display(root);
	}

}
