package processmanager;

import filemanager.Directory;
import filemanager.Disk;

public class MkdirEvent extends FileEvent {
	String name = null;
	Directory dir;

	public MkdirEvent(String name, Directory dir) {
		this.name = name;
		this.dir = dir;
		this.size = ProcessEvent.MKDIRSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.mkdir(p, name, dir);
	}
}
