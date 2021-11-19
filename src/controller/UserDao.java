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
		String sql = "INSERT INTO Tb_Usuario VALUES(?,?,?,?,?,?,?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getUserName());
		ps.setString(2, u.getContato());
		ps.setString(3, u.getSenha());
		ps.setString(4, u.getEmail());
		ps.setString(5, "F");
		ps.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
		ps.setTimestamp(7, null);
		ps.setTimestamp(8, null);
		
		ps.execute();
		ps.close();
	}

	@Override
	public User authUser(User u) throws SQLException {
		String sql = "SELECT * FROM Tb_Usuario WHERE email = ? AND senha = ? AND status_removido IS NULL";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		ps.setString(2, u.getSenha());
		
		ps.execute();
		
		ResultSet rs = ps.executeQuery();
		
		User user = new User();
		
		if(rs.next()) {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("userName"));
			user.setContato(rs.getString("contato"));
			user.setSenha(rs.getString("senha"));
			user.setEmail(rs.getString("email"));
			user.setPreferencias(rs.getString("status_preferencia"));
			user.setDataCriacao(rs.getTimestamp("data_Criacao"));
			user.setDataEdicao(rs.getTimestamp("data_Edicao"));
		} else {
			user = null;
		}
		
		return user;
	}
	
	@Override
	public User authDeletedUser(User u) throws SQLException {
		String sql = "SELECT * FROM Tb_Usuario WHERE email = ? AND senha = ? AND status_removido = 'T'";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		ps.setString(2, u.getSenha());
		
		ps.execute();
		
		ResultSet rs = ps.executeQuery();
		
		User user = new User();
		
		if(rs.next()) {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("userName"));
			user.setContato(rs.getString("contato"));
			user.setSenha(rs.getString("senha"));
			user.setEmail(rs.getString("email"));
			user.setPreferencias(rs.getString("status_preferencia"));
			user.setDataCriacao(rs.getTimestamp("data_Criacao"));
			user.setDataEdicao(rs.getTimestamp("data_Edicao"));
		} else {
			user = null;
		}
		
		return user;
	}

	@Override
	public void updateDeletedUser(User u) throws SQLException {
		String sql = "UPDATE Tb_Usuario"
				+ " SET status_removido = NULL"
				+ " WHERE email = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		
		ps.execute();
		ps.close();
	}
	
	@Override
	public void updateUser(User u) throws SQLException {
		String sql = "UPDATE Tb_Usuario"
				+ " SET userName = ?, contato = ?, senha = ?, email = ?, data_Edicao = ?"
				+ " WHERE id = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, u.getUserName());
		ps.setString(2, u.getContato());
		ps.setString(3, u.getSenha());
		ps.setString(4, u.getEmail());
		ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
		ps.setLong(6, u.getId());
		
		ps.execute();
		ps.close();
	}

	@Override
	public User getUserById(User u) throws SQLException {
		
		String sql = "SELECT * FROM Tb_Usuario WHERE id = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, u.getId());
		
		ps.execute();
		
		ResultSet rs = ps.executeQuery();
		
		User user = new User();
		
		if(rs.next()) {
			user.setId(rs.getLong("id"));
			user.setUserName(rs.getString("userName"));
			user.setContato(rs.getString("contato"));
			user.setSenha(rs.getString("senha"));
			user.setEmail(rs.getString("email"));
			user.setPreferencias(rs.getString("status_preferencia"));
			user.setDataCriacao(rs.getTimestamp("data_Criacao"));
			user.setDataEdicao(rs.getTimestamp("data_Edicao"));
		} else {
			user = null;
		}
		
		return user;
	}

	@Override
	public void deleteUser(User u) throws SQLException {
		String sql = "UPDATE Tb_Usuario"
				+ " SET status_removido = ?"
				+ " WHERE id = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(1, "T");
		ps.setLong(2, u.getId());
		
		ps.execute();
		ps.close();
		
	}
}