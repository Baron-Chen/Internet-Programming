package bean;

public class TableBean {
	private String tableId;
	private String tableType;
	private int totalCount;
	private int remainCount;
	private int waitCount=0;
	
	
	
	public int getWaitCount() {
		return waitCount;
	}
	public void setWaitCount(int waitCount) {
		this.waitCount = waitCount;
	}
	public TableBean() {
		super();
	}
	public TableBean(String tableType, int totalCount, int remainCount) {
		super();
		this.tableType = tableType;
		this.totalCount = totalCount;
		this.remainCount = remainCount;
	}
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	public String getTableType() {
		return tableType;
	}
	public void setTableType(String tableType) {
		this.tableType = tableType;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getRemainCount() {
		return remainCount;
	}
	public void setRemainCount(int remainCount) {
		this.remainCount = remainCount;
	}
	
	

}
