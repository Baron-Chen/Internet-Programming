package bean;

public class MealBean {
	private String mealId;
	private String mealName;
	private String mealType;
	private int count=0;
	private int price;
	
	
	public MealBean(String mealName, String mealType, int count, int price) {
		super();
		this.mealName = mealName;
		this.mealType = mealType;
		this.count = count;
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public MealBean(String mealName, String mealType, int price) {
		super();
		this.mealName = mealName;
		this.mealType = mealType;
		this.price = price;
	}
	public String getMealId() {
		return mealId;
	}
	public void setMealId(String mealId) {
		this.mealId = mealId;
	}
	public String getMealName() {
		return mealName;
	}
	public void setMealName(String mealName) {
		this.mealName = mealName;
	}
	public String getMealType() {
		return mealType;
	}
	public void setMealType(String mealType) {
		this.mealType = mealType;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
