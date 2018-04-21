package bean;

public class CustomerBean {
	private String customId;
	private String contactNum;
	private String gender;
	
	
	public CustomerBean(String contactNum, String gender) {
		super();
		this.contactNum = contactNum;
		this.gender = gender;
	}
	public String getCustomId() {
		return customId;
	}
	public void setCustomId(String customId) {
		this.customId = customId;
	}
	public String getContactNum() {
		return contactNum;
	}
	public void setContactNum(String contactNum) {
		this.contactNum = contactNum;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	

}
