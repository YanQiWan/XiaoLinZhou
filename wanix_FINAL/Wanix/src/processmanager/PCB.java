package processmanager;

import java.util.*;

import filemanager.Directory;
import filemanager.File;

public class PCB {
	public final static int RUN = 1;
	public final static int BLOCK = 2;
	public final static int READY = 3;
	private static int num = 0;
	private long id;// ���̱�ʶ��
	private int state;// ����״̬
	private String name;// ������
	private long size;// ���̴�С
	private Group permission;// ����������
	private Directory dir;// ��������Ŀ¼
	private List<File> files = new ArrayList<File>();

	public List<File> getFiles() {
		return files;
	}

	public PCB(String name) {
		this.id = num;
		this.state = READY;
		this.name = name;
		num++;
	}

	public PCB(String name, Group p, Directory dir) {
		this(name);
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

	private long executedTime = 0;

	public long getExecutedTime() {
		return executedTime;
	}

	public void setExecutedTime(long executedTime) {
		this.executedTime = executedTime;
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
