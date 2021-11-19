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
	public Boolean authDeletedUser(User u) throws ClassNotFoundException, SQLException {
		UserDao uDao = new UserDao();
		
		User userLog = uDao.authDeletedUser(u);
		
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

	@Override
	public void updateUser(User u) throws ClassNotFoundException, SQLException {
		UserDao uDao = new UserDao();
		
		uDao.updateUser(u);
		
		User userLog = uDao.getUserById(u);
		
		UserLogado.setUserLogado(userLog);
	}
	
	@Override
	public void updateDeletedUser(User u) throws ClassNotFoundException, SQLException {
		UserDao uDao = new UserDao();
		
		uDao.updateDeletedUser(u);		
	}

	@Override
	public void deleteUser(User u) throws ClassNotFoundException, SQLException {
		UserDao uDao = new UserDao();
		
		uDao.deleteUser(u);
	}
}