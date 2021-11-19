package controller;

import java.sql.SQLException;

import entity.User;

public interface IUserController {
	
	public Boolean authUser(User u) throws ClassNotFoundException, SQLException;
	public Boolean authDeletedUser(User u) throws ClassNotFoundException, SQLException;
	public void createUser(User u) throws ClassNotFoundException, SQLException;
	public void updateUser(User u) throws ClassNotFoundException, SQLException;
	public void updateDeletedUser(User u) throws ClassNotFoundException, SQLException;
	public void deleteUser (User u) throws ClassNotFoundException, SQLException;

}
