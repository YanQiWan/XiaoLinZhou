package filemanager;

import java.util.ArrayList;
import java.util.List;

import processmanager.Group;
import processmanager.Process;

public class Inode {

	private String filename;
	private int id;
	private FileType type;
	private Group permission;
	private int block_num = 1;
	private List<Integer> disk_block_id = new ArrayList<Integer>();
	private long time;
	private Directory parent;
	private Process p = null;

	public Process getP() {
		return p;
	}

	public void setP(Process p) {
		this.p = p;
	}

	public boolean isOccupied() {
		return p != null;
	}

	public Directory getParent() {
		return parent;
	}

	public void setParent(Directory parent) {
		this.parent = parent;
	}

	public List<Integer> getDisk_block_id() {
		return disk_block_id;
	}

	public void setDisk_block_id(List<Integer> disk_block_id) {
		this.disk_block_id = disk_block_id;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FileType getType() {
		return type;
	}

	public void setType(FileType type) {
		this.type = type;
	}

	public Group getPermission() {
		return permission;
	}

	public void setPermission(Group permission) {
		this.permission = permission;
	}

	public int getBlock_num() {
		return block_num;
	}

	public void setBlock_num(int block_num) {
		this.block_num = block_num;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}
}
