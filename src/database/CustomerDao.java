package database;

import java.sql.SQLException;
//import java.util.List;
import bean.CustomerBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.ArrayList;




public class CustomerDao {
	
    //add a new record
	//@Override
    public static void add(CustomerBean p)throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into customer(contactNum,gender)values(?,?)";
		try {
			 conn = DBUtils.getConnection();
	    	 ps = conn.prepareStatement(sql);
	    	 ps.setString(1, p.getContactNum());
	    	 ps.setString(2, p.getGender());
	    	 ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("add data failed");
		}finally {
			DBUtils.close(null, ps, conn);
		}		
    }   
	   
    //update the database
    public static void update(CustomerBean p)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "update customer set contactNum=?,gender=? where customId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
	    	ps.setString(1, p.getContactNum());
	    	ps.setString(2, p.getGender());
	    	ps.setString(3, p.getCustomId());    		
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("update data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}
    }
    
    //delete the data 
    public static void delete(String customId)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "delete from customer where customId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
	    	ps.setString(1, customId); 	    	
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("delete data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}  	
    }
    
    
    //customer check
    public static boolean IsUserExit(String contactNum)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	boolean result;
    	String sql = "select customId from customer where contactNum=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, contactNum);    		
    		rs = ps.executeQuery();
    		if(rs.next()) {
    			result = true;
    		}
    		else {
    			result = false;
    		}
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("check the customer data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}
    	   	
    	return result;
    	
    }
       
}
