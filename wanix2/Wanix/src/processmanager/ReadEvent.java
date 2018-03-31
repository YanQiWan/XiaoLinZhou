package processmanager;

import filemanager.Disk;
import filemanager.Document;

public class ReadEvent extends FileEvent {

	String doc;

	public ReadEvent(String doc) {
		this.doc = doc;

		this.size = ProcessEvent.READSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.read(p, (Document) root.files.get(doc));
	}

}
