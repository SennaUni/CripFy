package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import entity.Carteira;

public interface IMinhaCarteiraController {

	public void removeCoin(Carteira m) throws ClassNotFoundException, SQLException;
	public void addCoin(Carteira m) throws ClassNotFoundException, SQLException;
	public ArrayList<Carteira> selectCarteira() throws ClassNotFoundException, SQLException, ParseException;
	public void updateCoin(Carteira m) throws ClassNotFoundException, SQLException;
}
