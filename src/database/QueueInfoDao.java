package database;

import java.sql.SQLException;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

import bean.MealBean;
import bean.QueueInfoBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.ArrayList;



public class QueueInfoDao {

	//add a new record
	public static void add(QueueInfoBean p)throws SQLException{
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into queueinfo"+p.getTableType()+"(queueNumber,tableType,waittingTime,members,waittingCount,isMissed)values(?,?,?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setString(1, p.getQueueNumber());
			ps.setString(2, p.getTableType());
			ps.setInt(3, p.getWaittingTime());
			ps.setInt(4, p.getMembers());
			ps.setInt(5, p.getWaittingCount());
			ps.setString(6, p.getIsMissed());
			
			ps.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SQLException("add data failed");
		}finally {
			DBUtils.close(null, ps, conn);
		}		
	}	
	
    //update the database
    public static void update(QueueInfoBean p)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;    	
    	String sql = "update queueinfo"+p.getTableType()+" set queueNumber=?,tableType=?,waittingTime=?,members=?,waittingCount=?,isMissed=? where queueId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql); 
    		
			ps.setString(1, p.getQueueNumber());
			ps.setString(2, p.getTableType());
			ps.setInt(3, p.getWaittingTime());
			ps.setInt(4, p.getMembers());
			ps.setInt(5, p.getWaittingCount());
			ps.setString(6, p.getIsMissed());
			ps.setInt(7, p.getQueueId());
						
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("update data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}
    }	
	
    //delete the data 
    public static void delete(QueueInfoBean p)throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "delete from queueinfo"+p.getTableType()+" where queueId=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		
	    	ps.setInt(1, p.getQueueId()); 	    	
    		ps.executeUpdate();
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("delete data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}  	
    }
    
    //search data by queueid
    public static QueueInfoBean findByNum(String 	queueNum,String type) throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	QueueInfoBean p = null;
    	String sql = "select queueNumber,tableType,waittingTime,members,waittingCount,isMissed from queueinfo"+type+" where queueNumber=?";
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		ps.setString(1, queueNum); 
    		rs = ps.executeQuery();
    		if(rs.next()){
    			p = new QueueInfoBean();
    			p.setQueueId(p.getQueueId());
    			p.setQueueNumber(rs.getString(1));
    			p.setTableType(rs.getString(2));
    			p.setWaittingTime(rs.getInt(3));
    			p.setMembers(rs.getInt(4));
    			p.setWaittingCount(rs.getInt(5));
    			p.setIsMissed(rs.getString(6));
    		}  		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("search data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}  
    	
    	return p;
    	
    }    
    
    public static List<QueueInfoBean> findAll(String type) throws SQLException{
    	Connection conn = null;
    	PreparedStatement ps = null;
    	ResultSet rs = null;
    	QueueInfoBean p = null;
    	
    	List<QueueInfoBean> queue = new ArrayList<QueueInfoBean>();
    	String sql = "select queueId,queueNumber,tableType,waittingTime,members,waittingCount,isMissed from queueinfo"+type;
    	try {
    		conn = DBUtils.getConnection();
    		ps = conn.prepareStatement(sql);
    		rs = ps.executeQuery();
    		while(rs.next()) {
    			p = new QueueInfoBean();//????
    			p.setQueueId(rs.getInt(1));
    			p.setQueueNumber(rs.getString(2));
    			p.setTableType(rs.getString(3));
    			p.setWaittingTime(rs.getInt(4));
    			p.setMembers(rs.getInt(5));
    			p.setWaittingCount(rs.getInt(6));
    			p.setIsMissed(rs.getString(7));
    			queue.add(p);
    		}   		
    		
    	}catch(SQLException e) {
    		e.printStackTrace();
    		throw new SQLException("find all data failed");
    	}finally {
    		DBUtils.close(null, ps, conn);
    	}
    	
    	 return queue;
    	
    			
    }
    
    
	
	
	
}
