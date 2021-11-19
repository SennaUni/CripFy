package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import entity.Carteira;

public class MinhaCarteiraController implements IMinhaCarteiraController{

	@Override
	public void removeCoin(Carteira m) throws ClassNotFoundException, SQLException {
		MinhaCarteiraDao cDao = new MinhaCarteiraDao();
		
		cDao.removeCoin(m);
	}

	@Override
	public void addCoin(Carteira m) throws ClassNotFoundException, SQLException {
		MinhaCarteiraDao cDao = new MinhaCarteiraDao();

		cDao.addCoin(m);
	}

	@Override
	public ArrayList<Carteira> selectCarteira() throws ClassNotFoundException, SQLException, ParseException {
		ArrayList<Carteira> listaCarteira = new ArrayList<Carteira>();
		
		MinhaCarteiraDao cDao = new MinhaCarteiraDao();
		
		listaCarteira = cDao.selectCarteira();
		
		return listaCarteira;
	}

	@Override
	public void updateCoin(Carteira m) throws ClassNotFoundException, SQLException {
		MinhaCarteiraDao cDao = new MinhaCarteiraDao();
		
		cDao.updateCoin(m);
	}

}
