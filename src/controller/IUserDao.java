package controller;

import java.sql.SQLException;

import entity.User;

public interface IUserDao {

	public void createUser(User u) throws SQLException;
	public Boolean authUser(User u) throws SQLException;
}