package filemanager;

import java.util.ArrayList;
import java.util.List;

import processmanager.Group;
import processmanager.Process;

public class FileController {
	private static FileController fileController = null;
	private static final int DISKSIZE = 500;
	public static Directory root = new Directory();
	// private static Directory current_dir;
	private Disk disk;

	private FileController() {
		this.disk = new Disk(DISKSIZE);
		root.getInode().setFilename("root");
		root.getInode().setPermission(Group.SUPER);
		diskAllocate(root);
	}

	public static FileController getFileController() {
		if (fileController == null)
			fileController = new FileController();
		return fileController;
	}

	private File create(Process p, String name, Directory dir, FileType t) throws Exception {

		if (dir == null) {
			dir = p.pcb.getDir();
		}
		String className = null;
		if (t.compareTo(FileType.DOCUMENT) == 0) {
			className = "filemanager.Document";
		} else if (t.compareTo(FileType.DIRECTORY) == 0) {
			className = "filemanager.Directory";
		}
		File newFile = (File) Class.forName(className).newInstance();
		if (diskAllocate(newFile)) {
			newFile.getInode().setTime(System.currentTimeMillis());
			newFile.getInode().setType(t);
			newFile.getInode().setFilename(name);
			newFile.setInodeID();
			newFile.getInode().setParent(dir);
			newFile.getInode().setPermission(p.pcb.getPermission());
			dir.files.put(name, newFile);
			return newFile;
		}
		return null;
	}

	public Document touch(Process p, String name, Directory dir) throws Exception {
		return (Document) create(p, name, dir, FileType.DOCUMENT);
	}

	public Directory mkdir(Process p, String name, Directory dir) throws Exception {
		return (Directory) create(p, name, dir, FileType.DIRECTORY);
	}

	public boolean isRemovable(Process p, File file) {
		if (p.pcb.getPermission().compareTo(file.getInode().getPermission()) >= 0) {
			if (file instanceof Document) {
				return true;
			} else if (file instanceof Directory) {
				List<File> files = new ArrayList<File>(((Directory) file).files.values());
				for (File f : files) {
					if (!isRemovable(p, f)) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean remove(Process p, Document doc) {
		boolean flag;
		if (isRemovable(p, doc) && !doc.getInode().isOccupied()) {
			diskRelease(doc);
			doc.getInode().getParent().files.remove(doc.getInode().getFilename());
			flag = true;
		} else
			flag = false;
		return flag;
	}

	public boolean rmdir(Process p, Directory dir) {
		boolean flag;
		if (isRemovable(p, dir) && !dir.getInode().isOccupied()) {
			diskRelease(dir);
			dir.getInode().getParent().files.remove(dir.getInode().getFilename());
			if (!dir.files.isEmpty()) {
				List<File> files = new ArrayList<File>((dir).files.values());
				for (File file : files) {
					if (file instanceof Document) {
						Document d = (Document) file;
						remove(p, d);
					} else {
						Directory d = (Directory) file;
						diskRelease(d);
						rmdir(p, d);
					}
				}
			}
			flag = true;
			dir.getInode().getParent().files.remove(dir.getInode().getFilename());
		} else {
			flag = false;
		}
		return flag;
	}

	private boolean diskAllocate(File file) {
		int blocknum = file.getInode().getBlock_num();
		if (disk.getRemainnum() >= blocknum) {
			file.getInode().setBlock_num(blocknum);
			disk.setRemainnum(disk.getRemainnum() - blocknum);
			for (DiskBlock db : disk.getBlocks()) {
				if (db.isAvailable()) {
					blocknum--;
					db.setAvailable(false);
					file.getInode().getDisk_block_id().add(db.getId());
					db.f = file;
					if (blocknum == 0)
						break;
				}
			}
			return true;
		} else
			return false;
	}

	private boolean diskRelease(File file) {
		List<Integer> disk_block_id = file.getInode().getDisk_block_id();
		for (Integer i : disk_block_id) {
			disk.getBlocks()[i].setAvailable(true);
		}
		disk.setRemainnum(disk.getRemainnum() + disk_block_id.size());
		file.getInode().setBlock_num(0);

		return true;
	}

	public static void display(File file) {
		displayRecursion(file, 0);
	}

	private static void displayRecursion(File file, int n) {

		for (int i = 0; i < n; i++)
			System.out.print(" ");
		System.out.println(file.getInode().getFilename());
		if (file instanceof Directory) {
			List<File> files = new ArrayList<File>(((Directory) file).files.values());
			for (File f : files) {
				displayRecursion(f, n + 1);
			}
		}

	}

	public boolean open(Process p, Document document) {
		if (!document.getInode().isOccupied()
				&& p.pcb.getPermission().compareTo(document.getInode().getPermission()) >= 0) {
			document.getInode().setP(p);
			p.pcb.getFiles().add(document);
			return true;
		}
		return false;
	}

	public boolean close(Process p, Document document) {
		if (document.getInode().isOccupied() && document.getInode().getP().equals(p)) {
			document.getInode().getP().pcb.getFiles().remove(document);
			document.getInode().setP(null);
			return true;
		} else
			return false;
	}

	public boolean isWritable(Document document, String content) {
		return (int) Math.ceil((double) (content.length() + 1) / (double) DiskBlock.SIZE) <= (disk.getRemainnum()
				+ document.getInode().getBlock_num());
	}

	public boolean write(Process p, Document document, String content) {
		if (isWritable(document, content)) {
			diskRelease(document);
			document.setContent(content);
			diskAllocate(document);
			return true;
		} else
			return false;
	}

	public String read(Process p, Document document) {
		String content = null;
		Process tmp = document.getInode().getP();
		if (tmp != null) {
			if (tmp.equals(p)) {
				content = document.getContent();
			}
		}
		return content;
	}

	public boolean isModeChangable(Process p, File f, Group g) {
		return (!f.getInode().isOccupied() && p.pcb.getPermission().compareTo(f.getInode().getPermission()) >= 0
				&& p.pcb.getPermission().compareTo(g) >= 0);
	}

	public boolean chmod(Process p, File f, Group g) {
		if (isModeChangable(p, f, g)) {
			f.getInode().setPermission(g);
			return true;
		}
		return false;
	}
}
