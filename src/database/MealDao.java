package database;

import java.sql.SQLException;
import java.util.List;


import bean.MealBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class MealDao {
	
	//add a new record
	public static void add(MealBean p)throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into meal(mealName,mealType,price)values(?,?,?)";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, p.getMealName());
			ps.setString(2, p.getMealType());
			ps.setInt(3, p.getPrice());
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("add data failed");
		}finally {
			DBUtils.close(null, ps, conn);
		}		
	}
	
    //update the database
    public static void update(MealBean p)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;    	
    	String sql = "update meal set mealName=?,mealType=?,price=? where mealId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);    		
			ps.setString(1, p.getMealName());
			ps.setString(2, p.getMealType());
			ps.setInt(3, p.getPrice());
			ps.setString(4, p.getMealId());			
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("update data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}
    }
    
    //delete the data 
    public static void delete(String mealId)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "delete from meal where mealId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
	    	ps.setString(1, mealId); 	    	
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("delete data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}  	
    } 
    
    //find all the data in the meal
    public static List<MealBean> findAll() throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	MealBean p = null;
    	
    	List<MealBean> meals = new ArrayList<MealBean>();
    	String sql = "select mealId,mealName,mealType, price from meal";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			p = new MealBean(null, null, 0);//????
    			p.setMealId(rs.getString(1));
    			p.setMealName(rs.getString(2));
    			p.setMealType(rs.getString(3));
    			p.setPrice(rs.getInt(4));
    			meals.add(p);
    		}   		
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("find all data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}
    	
    	 return meals;
    	
    			
    }
  
}
