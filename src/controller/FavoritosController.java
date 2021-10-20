package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Favorito;
import entity.Moeda;

public class FavoritosController implements IFavoritosController{

	@Override
	public void removeCoin(Favorito fav) throws ClassNotFoundException, SQLException {
		FavoritosDao fDao = new FavoritosDao();
		
		fDao.removeCoin(fav);
	}

	@Override
	public void addCoin(Moeda m) throws ClassNotFoundException, SQLException {
		FavoritosDao fDao = new FavoritosDao();
		
		fDao.addCoin(m);
	}

	@Override
	public ArrayList<Moeda> selectCoins() throws ClassNotFoundException, SQLException {
		ArrayList<Moeda> listaDeMoedas = new ArrayList<Moeda>();
		
		FavoritosDao fDao = new FavoritosDao();
		
		listaDeMoedas = fDao.selectCoins();
		
		return listaDeMoedas;
	}

	@Override
	public ArrayList<Favorito> selectFav() throws ClassNotFoundException, SQLException {
		ArrayList<Favorito> listaFavoritos = new ArrayList<Favorito>();
		
		FavoritosDao fDao = new FavoritosDao();
		
		listaFavoritos = fDao.selectFav();
		
		return listaFavoritos;
	}

}
