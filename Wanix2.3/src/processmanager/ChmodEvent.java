package processmanager;

import filemanager.Disk;
import filemanager.Document;
import filemanager.File;

public class ChmodEvent extends FileEvent {

	String file;
	Group g;

	public ChmodEvent(String file, Group g) {
		this.file = file;
		this.g = g;
		this.size = ProcessEvent.CHMODSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.chmod(p, root.files.get(file), g);
	}

}
