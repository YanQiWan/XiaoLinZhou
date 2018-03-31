package processmanager;

import filemanager.Directory;
import filemanager.Disk;

public class RmdirEvent extends FileEvent {

	Directory dir;

	public RmdirEvent(Directory dir) {
		this.dir = dir;
		this.size = ProcessEvent.RMDIRSIZE;
	}

	void handleEvent() throws Exception {
		fr.rmdir(p, dir);
	}

}
