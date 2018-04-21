package bean;

import java.util.ArrayList;

public class QueueInfoBean {
	private int queueId;	
	private String queueNumber;
	private String tableType;
	private int waittingTime;
	private int members;
	private int waittingCount;
	private String isMissed="false";
	
	public String getIsMissed() {
		return isMissed;
	}
	public void setIsMissed(String isMissed) {
		this.isMissed = isMissed;
	}
	public static int currentCountA=0;
	//public static ArrayList<QueueInfoBean> queueA=new ArrayList<QueueInfoBean>();
	public static int currentCountB=0;
	//public static ArrayList<QueueInfoBean> queueB=new ArrayList<QueueInfoBean>();
	public static int currentCountC=0;
	//public static ArrayList<QueueInfoBean> queueC=new ArrayList<QueueInfoBean>();
	
	
	public QueueInfoBean() {
		super();
	}
	public QueueInfoBean(String queueNumber, String tableType, int waittingTime, int members, int waittingCount) {
		super();
		this.queueNumber = queueNumber;
		this.tableType = tableType;
		this.waittingTime = waittingTime;
		this.members = members;
		this.waittingCount = waittingCount;
	}
	public int getQueueId() {
		return queueId;
	}
	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}
	public String getQueueNumber() {
		return queueNumber;
	}
	public void setQueueNumber(String queueNumber) {
		this.queueNumber = queueNumber;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public int getWaittingTime() {
		return waittingTime;
	}
	public void setWaittingTime(int waittingTime) {
		this.waittingTime = waittingTime;
	}
	public int getMembers() {
		return members;
	}
	public void setMembers(int members) {
		this.members = members;
	}
	public int getWaittingCount() {
		return waittingCount;
	}
	public void setWaittingCount(int waittingCount) {
		this.waittingCount = waittingCount;
	}
	
	
	
	
	

}
