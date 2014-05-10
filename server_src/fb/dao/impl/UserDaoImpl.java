package fb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fb.dao.UserDao;
import fb.entity.User;
import fb.util.DBUtil;

public class UserDaoImpl implements UserDao {

	@Override
	public boolean isUserExisted(String username) {
		String sql = "select username from user where username=?";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				String name = rs.getString("username");
				if (name != null)
					return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertNewUser(User user) {
		String sql = "insert into user values (?,?,?,?)";
		DBUtil util = new DBUtil();
		Connection conn = util.getConn();

		try {
			PreparedStatement pstmt = (PreparedStatement) conn
					.prepareStatement(sql);
			pstmt.setString(1, user.getUsername());
			pstmt.setString(2, user.getPhone());
			pstmt.setString(3, user.getMail());
			pstmt.setString(4, user.getCreditcard());
			
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
