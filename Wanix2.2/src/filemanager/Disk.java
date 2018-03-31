package filemanager;

public class Disk {
	private int blocknum;
	private static DiskBlock blocks[];
	private int remainnum;

	public Disk(int blocknum) {
		this.blocknum = blocknum;
		remainnum = blocknum;
		blocks = new DiskBlock[this.blocknum];
		for (int i = 0; i < this.blocknum; i++) {
			blocks[i] = new DiskBlock(i);
		}
	}

	public int getRemainnum() {
		return remainnum;
	}

	public void setRemainnum(int remainnum) {
		this.remainnum = remainnum;
	}

	public int getBlocknum() {
		return blocknum;
	}

	public DiskBlock[] getBlocks() {
		return blocks;
	}

	public void display() {
		for (int i = 0; i < blocknum; i++) {
			if (!blocks[i].isAvailable())
				System.out.println(blocks[i].getId() + "\t" + blocks[i].isAvailable() + "\t"
						+ blocks[i].f.getInode().getFilename());
			else
				System.out.println(blocks[i].getId() + "\t" + blocks[i].isAvailable() + "\t");
		}
	}
}
