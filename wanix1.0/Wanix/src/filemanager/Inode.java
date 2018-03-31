package filemanager;

import processmanager.Group;

public class Inode {
	private String filename;
	private int inode;
	private FileType type;
	private Group permission;
	private int block_num;
	private int[] disk_block_number;
	private long time;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public int getInode() {
		return inode;
	}
	public void setInode(int inode) {
		this.inode = inode;
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
	public int[] getDisk_block_number() {
		return disk_block_number;
	}
	public void setDisk_block_number(int[] disk_block_number) {
		this.disk_block_number = disk_block_number;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
}
