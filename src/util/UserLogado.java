package util;

import entity.User;

public class UserLogado {

	private static User UserLogado;

	public static User getUserLogado() {
		return UserLogado;
	}

	public static void setUserLogado(User userLogado) {
		UserLogado = userLogado;
	}
	
	public static String getUserName() {
		return UserLogado.getUserName();
	}
	
	public static Long getUserId() {
		return UserLogado.getId();
	}
}
