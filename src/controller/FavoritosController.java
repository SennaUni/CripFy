package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.Moeda;

public class FavoritosController implements IFavoritosController{

	@Override
	public void removeCoin(Moeda m) throws ClassNotFoundException, SQLException {
		
	}

	@Override
	public void addCoin(Moeda m) throws ClassNotFoundException, SQLException {
		
	}

	@Override
	public ArrayList<Moeda> selectAll() throws ClassNotFoundException, SQLException {
		ArrayList<Moeda> listaDeMoedas = new ArrayList<Moeda>();
		
		FavoritosDao fDao = new FavoritosDao();
		
		listaDeMoedas = fDao.selectAll();
		
		return listaDeMoedas;
	}

}
