package filemanager;

public class DiskBlock {
	public static final int SIZE = 16;
	private int id;
	private boolean available = true;
	public File f;

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
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
