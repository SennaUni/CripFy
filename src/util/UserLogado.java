package util;

import java.util.List;

import entity.Moeda;
import entity.User;

public class UserLogado {

	private static User UserLogado;
	private static List<Moeda> listaFavoritos;

	public static User getUserLogado() {
		return UserLogado;
	}

	public static void setUserLogado(User userLogado) {
		UserLogado = userLogado;
	}
	
	public static void LogOffUser() {
		UserLogado = null;
	}
	
	public static String getUserName() {
		return UserLogado.getUserName();
	}
	
	public static Long getUserId() {
		return UserLogado.getId();
	}
	
	public static String getUserEmail() {
		return UserLogado.getEmail();
	}
	
	public static String getUserContato() {
		return UserLogado.getContato();
	}
	
	public static String getUserPassword() {
		return UserLogado.getSenha();
	}
}
