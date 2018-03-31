package processmanager;

import filemanager.Directory;
import filemanager.Disk;

public class RmdirEvent extends FileEvent {

	String dir;

	public RmdirEvent(String dir) {
		this.dir = dir;
		this.size = ProcessEvent.RMDIRSIZE;
	}

	void handleEvent() throws Exception {
		fr.rmdir(p, (Directory) root.files.get(dir));
	}

}
