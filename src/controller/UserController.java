package controller;

import java.sql.SQLException;

import entity.User;

public class UserController implements IUserController{

	@Override
	public Boolean authUser(User u) throws ClassNotFoundException, SQLException {	
		UserDao uDao = new UserDao();
		
		Boolean logIn = uDao.authUser(u);
		
		return logIn;
	}

	@Override
	public void createUser(User u) throws ClassNotFoundException, SQLException {
		UserDao uDao = new UserDao();
		
		uDao.createUser(u);
	}
}