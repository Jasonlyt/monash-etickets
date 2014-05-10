package fb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fb.dao.OrderDao;
import fb.entity.Order;
import fb.util.DBUtil;

public class OrderDaoImpl implements OrderDao {

	@Override
	public boolean isOrderExisted(String fid, String username) {
		String sql = "select fid from orders where fid=? and username=? ";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fid);
			pstmt.setString(2, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("fid");
				if (name != null)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertNewOrder(Order order) {
		String sql = "insert into orders values (?,?,?) ";

		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		try {
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sql);
			pstmt.setInt(1, order.getOid());
			pstmt.setString(2, order.getFid());
			pstmt.setString(3, order.getUsername());
			
			String flag = new String("");
			synchronized (flag) {
				int state = pstmt.executeUpdate();
				if (state == 1)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

}
