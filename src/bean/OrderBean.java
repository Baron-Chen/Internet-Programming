package bean;

import java.util.ArrayList;

public class OrderBean {
	private String orderId;
	private String queueNumber;
	private ArrayList<MealBean> orderDetails;
	private int totalPrice;
	
	
	public OrderBean() {
		super();
	}
	public OrderBean(String queueNumber, ArrayList<MealBean> orderDetails, int totalPrice) {
		super();
		this.queueNumber = queueNumber;
		this.orderDetails = orderDetails;
		this.totalPrice = totalPrice;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getQueueNumber() {
		return queueNumber;
	}
	public void setQueueNumber(String queueNumber) {
		this.queueNumber = queueNumber;
	}
	public ArrayList<MealBean> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(ArrayList<MealBean> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public String getOrderDetailString() {
		String orderDetailString="";
		if(this.orderDetails!=null) {
			for (MealBean meal : orderDetails) {
				orderDetailString+=meal.getMealName()+" "+meal.getMealType()+" "+String.valueOf(meal.getPrice())+",";
			}
		}
		return orderDetailString;
	}

	

}
