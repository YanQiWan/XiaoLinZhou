package processmanager;

import java.util.*;

import filemanager.Directory;
import filemanager.File;

public class PCB {
	public final static int RUN = 1;
	public final static int BLOCK = 2;
	public final static int READY = 3;
	private long id;// 进程标识符
	private static int count = 0;
	private int state;// 进程状态
	private String name;// 进程名
	private long size;// 进程大小
	private Group permission;// 进程所属组
	private Directory dir;// 进程所在目录
	private List<File> files = new ArrayList<File>();

	public List<File> getFiles() {
		return files;
	}


	public PCB(String name, Group p, Directory dir) {
		this.state = READY;
		this.name = name;
		id = count;
		count++;
		this.permission = p;
		this.dir = dir;
		
	}

	public Group getPermission() {
		return permission;
	}

	public void setPermission(Group permission) {
		this.permission = permission;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getName() {
		return name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Directory getDir() {
		return dir;
	}

	public void setDir(Directory dir) {
		this.dir = dir;
	}
}
