package processmanager;

import java.util.*;

import memorymanager.PartitionItem;

public class PCB {
	public final static int RUN = 1;
	public final static int BLOCK = 2;
	public final static int READY = 3;
	private long id;// ���̱�ʶ��
	private int state;// ����״̬
	private int priority;// �������ȼ�
	private String name;//������
	private long size;//���̴�С
	private Group permission;
	

	
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

	private long neededTime;
	private List<Semaphore> sems = new ArrayList<Semaphore>();// �ź���

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

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public List<Semaphore> getSems() {
		return sems;
	}

	public void setSems(List<Semaphore> sems) {
		this.sems = sems;
	}

	public PCB(long id, int priority, long neededTime, String name) {
		this.id = id;
		this.state = READY;
		this.priority = priority;
		this.neededTime = neededTime;
		this.name = name;
	}
}
