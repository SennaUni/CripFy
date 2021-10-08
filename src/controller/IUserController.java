package controller;

import java.sql.SQLException;

import entity.User;

public interface IUserController {
	
	public Boolean authUser(User u) throws ClassNotFoundException, SQLException;
	public void createUser(User u) throws ClassNotFoundException, SQLException;
}
