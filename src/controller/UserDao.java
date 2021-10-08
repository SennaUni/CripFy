package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import db.DBConnection;
import entity.User;

public class UserDao implements IUserDao{
	
	private Connection c;

	public UserDao() throws ClassNotFoundException, SQLException {
		
		DBConnection cDao = new DBConnection();
		c = cDao.getConnection();
	}

	@Override
	public void createUser(User u) throws SQLException {
		String sql = "INSERT INTO Tb_Usuario VALUES(?,?,?,?,?,?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getUserName());
		ps.setString(2, u.getContato());
		ps.setString(3, u.getSenha());
		ps.setString(4, u.getEmail());
		ps.setString(5, "F");
		ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
		ps.setTimestamp(7, null);
		
		ps.execute();
		ps.close();
	}

	@Override
	public Boolean authUser(User u) throws SQLException {
		String sql = "SELECT * FROM Tb_Usuario WHERE email = ? AND senha = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		ps.setString(2, u.getSenha());
		
		ps.execute();
		
		ResultSet rs = ps.executeQuery();
		
		System.out.println(rs);
		
		if (rs.next()) {
			ps.close();
			return true;
		} else {
			ps.close();
			return false;
		}
	}
}