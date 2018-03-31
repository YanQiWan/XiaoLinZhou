package processmanager;

import filemanager.Directory;
import filemanager.Disk;

public class TouchEvent extends FileEvent {
	String name = null;
	Directory dir;

	public TouchEvent(String name, Directory dir) {
		this.name = name;
		this.dir = dir;
		this.size = ProcessEvent.TOUCHSIZE;
	}

	@Override
	void handleEvent() throws Exception {
		fr.touch(p, name, dir);
		fr.display(fr.root);
	}

}
