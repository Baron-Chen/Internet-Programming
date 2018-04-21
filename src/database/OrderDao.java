package database;

import java.sql.SQLException;
import java.util.List;

import bean.MealBean;
import bean.OrderBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class OrderDao {
	
	//add a new record
	public static void add(OrderBean p)throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into orderd(queueNumber,orderDetails,totalPrice)values(?,?,?)";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getQueueNumber());
			ps.setString(2, p.getOrderDetailString());//????
			ps.setInt(3, p.getTotalPrice());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("add data failed");
		}finally {
			DBUtils.close(null, ps, conn);
		}		
	}	
	
	
    //update the database
    public static void update(OrderBean p)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;    	
    	String sql = "update orderd set queueNumber=?,orderDetails=?,totalPrice=? where orderId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);    		
			ps.setString(1, p.getQueueNumber());
			ps.setString(2, p.getOrderDetailString());//????
			ps.setInt(3, p.getTotalPrice());
			ps.setString(4, p.getOrderId());			
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("update data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}
    }
	
    
    //delete the data 
    public static void delete(String orderId)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "delete from orderd where orderId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
	    	ps.setString(1, orderId); 	    	
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("delete data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}  	
    } 
	
    //search data by orderid
    public static OrderBean findById(String orderId) throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	OrderBean p = null;
    	String sql = "select queueNumber,orderDetails,totalPrice from orderd where orderId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, orderId); 
    		rs = ps.executeQuery();
    		if(rs.next()){
    			p = new OrderBean();//?????
    			p.setOrderId(orderId);
    			p.setQueueNumber(rs.getString(1));
    			String orderDetails=rs.getString(2);
    			ArrayList<MealBean> temp=new ArrayList<MealBean>();
    			String[] meals=orderDetails.split(",");
    			for (String meal : meals) {
    				String[] m=meal.split(" ");
    				MealBean mealBean=new MealBean(m[0], m[1], Integer.valueOf(m[2]));
    				temp.add(mealBean);
    				System.out.println(mealBean.getMealName()+"--"+mealBean.getMealType()+"--"+mealBean.getPrice());
    			}
    			p.setOrderDetails(temp);//??????
    			p.setTotalPrice(rs.getInt(3));
    		}  		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("search data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}  
    	
    	return p;
    	
    }
   	
	

}
