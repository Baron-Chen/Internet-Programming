package database;

import java.sql.SQLException;
//import java.util.List;
import java.util.ArrayList;
import java.util.List;

import bean.TableBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.ArrayList;

public class TableDao {

	// add a new record
	public static void add(TableBean p) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into tables(tableType,totalCount,remainCount,waitCount)values(?,?,?,?)";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(1, p.getTableType());
			ps.setInt(2, p.getTotalCount());
			ps.setInt(3, p.getRemainCount());
			ps.setInt(4, p.getWaitCount());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("add data failed");
		} finally {
			DBUtils.close(null, ps, conn);
		}
	}

	// update the database
	public static void update(TableBean p) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "update tables set totalCount=?,remainCount=?,waitCount=? where tableType=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);

			ps.setString(4, p.getTableType());
			ps.setInt(1, p.getTotalCount());
			ps.setInt(2, p.getRemainCount());
			ps.setInt(3, p.getWaitCount());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("update data failed");
		} finally {
			DBUtils.close(null, ps, conn);
		}
	}

	// delete the data
	public static void delete(String tableId) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "delete from tables where tableId=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, tableId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("delete data failed");
		} finally {
			DBUtils.close(null, ps, conn);
		}
	}

	// find all the data in the meal
	public static List<TableBean> findAll() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TableBean p = null;

		List<TableBean> tables = new ArrayList<TableBean>();
		String sql = "select tableId,tableType,totalCount,remainCount,waitCount from tables";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				p = new TableBean();// ????
				p.setTableId(rs.getString(1));
				p.setTableType(rs.getString(2));
				p.setTotalCount(rs.getInt(3));
				p.setRemainCount(rs.getInt(4));
				p.setWaitCount(rs.getInt(5));
				tables.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("find all data failed");
		} finally {
			DBUtils.close(null, ps, conn);
		}

		return tables;
	}

	public static TableBean findByType(String type) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		TableBean p = null;

		String sql = "select tableId,tableType,totalCount,remainCount,waitCount from tables where tableType=?";
		try {
			conn = DBUtils.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, type);
			rs = ps.executeQuery();

			while (rs.next()) {
				p = new TableBean();// ????
				p.setTableId(rs.getString(1));
				p.setTableType(rs.getString(2));
				p.setTotalCount(rs.getInt(3));
				p.setRemainCount(rs.getInt(4));
				p.setWaitCount(rs.getInt(5));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("find all data failed");
		} finally {
			DBUtils.close(null, ps, conn);
		}

		return p;
	}

}
