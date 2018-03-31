package filemanager;

public class DiskBlock {
	public static final int SIZE = 16;
	private int id;
	public File f = null;

	public boolean isAvailable() {
		return f == null;
	}

	public DiskBlock(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
