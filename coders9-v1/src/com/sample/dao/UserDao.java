package com.sample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sample.utils.ConnectionUtil;
import com.sample.utils.QueryUtil;
import com.sample.vo.User;

public class UserDao {

	public void insertUser(User user) throws SQLException {
		
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(QueryUtil.getSQL("user.insertUser"));
		pstmt.setString(1, user.getName());
		pstmt.setString(2, user.getPassword());
		pstmt.setString(3, user.getEmail());
		pstmt.executeUpdate();
		
		pstmt.close();
		connection.close();
		
	}
	
	public User getUserByEmail(String userEmail) throws SQLException {
		User user = null;
		
		Connection connection = ConnectionUtil.getConnection();
		PreparedStatement pstmt = connection.prepareStatement(QueryUtil.getSQL("user.getUserByEmail"));
		pstmt.setString(1, userEmail);
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next()) {
			user = new User();
			user.setNo(rs.getInt("user_no"));
			user.setName(rs.getString("user_name"));
			user.setPassword(rs.getString("user_password"));
			user.setEmail(rs.getString("user_email"));
			user.setCreatedDate(rs.getDate("user_created_date"));
		}
		rs.close();
		pstmt.close();
		connection.close();
		
		return user;
	}
}
