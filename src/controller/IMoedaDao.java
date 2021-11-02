package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Moeda;

public interface IMoedaDao {
	public ArrayList<Moeda> selectCoins() throws ClassNotFoundException, SQLException;
}
