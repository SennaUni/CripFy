package controller;

import java.sql.SQLException;

import entity.User;

public interface IUserDao {

	public void createUser(User u) throws SQLException;
	public User authUser(User u) throws SQLException;
	public User getUserById(User u) throws SQLException;
	public void updateUser(User u) throws SQLException;
	public void deleteUser (User u) throws SQLException;
}