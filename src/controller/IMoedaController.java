package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Moeda;

public interface IMoedaController {
	public Moeda getApiMoeda() throws ClassNotFoundException, SQLException;
	public ArrayList<Moeda> selectCoins() throws ClassNotFoundException, SQLException;
}
