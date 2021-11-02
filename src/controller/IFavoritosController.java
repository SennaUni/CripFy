package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Favorito;
import entity.Moeda;

public interface IFavoritosController {

	public void removeCoin(Favorito fav) throws ClassNotFoundException, SQLException;
	public void addCoin(Moeda m) throws ClassNotFoundException, SQLException;
	public ArrayList<Favorito> selectFav() throws ClassNotFoundException, SQLException;
}
