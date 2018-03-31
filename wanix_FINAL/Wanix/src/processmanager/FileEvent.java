package processmanager;

import filemanager.Directory;
import filemanager.Disk;
import filemanager.File;
import filemanager.FileController;
import filemanager.Inode;

public abstract class FileEvent extends ProcessEvent {
	FileController fr;
	Directory root = FileController.root;

	public FileEvent() {
		fr = FileController.getFileController();
	}
}
