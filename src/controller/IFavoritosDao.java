package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Moeda;

public interface IFavoritosDao {

	public void addCoin(Moeda m) throws ClassNotFoundException, SQLException;
	public ArrayList<Moeda> selectAll() throws ClassNotFoundException, SQLException;
	public void removeCoin(Moeda m) throws ClassNotFoundException, SQLException;
}
