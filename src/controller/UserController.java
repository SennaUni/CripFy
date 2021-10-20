package controller;

import java.sql.SQLException;

import entity.User;
import util.UserLogado;

public class UserController implements IUserController{

	@Override
	public Boolean authUser(User u) throws ClassNotFoundException, SQLException {	
		UserDao uDao = new UserDao();
		
		User userLog = uDao.authUser(u);
		
		if(userLog == null) {
			return false;
		} else {
			UserLogado.setUserLogado(userLog);
			return true;
		}
	}

	@Override
	public void createUser(User u) throws ClassNotFoundException, SQLException {
		UserDao uDao = new UserDao();
		
		uDao.createUser(u);
	}
}