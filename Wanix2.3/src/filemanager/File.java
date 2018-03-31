package filemanager;

public class File {
	protected Inode inode = new Inode();
	public static int count = 0;

	public Inode getInode() {
		return inode;
	}

	public void setInodeID() {
		inode.setId(count);
		count++;
	}

}
