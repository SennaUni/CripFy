package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Moeda;

public interface IMoedaController {
	public ArrayList<Moeda> selectCoins() throws ClassNotFoundException, SQLException;
}
