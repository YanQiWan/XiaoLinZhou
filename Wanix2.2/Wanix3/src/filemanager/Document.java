package filemanager;

public class Document extends File {
	private String content = "";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
		this.inode.setBlock_num((int) Math.ceil((double) (content.length() + 1) / (double) DiskBlock.SIZE));
	}
}
