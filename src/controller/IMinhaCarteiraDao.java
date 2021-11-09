package controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import entity.Carteira;

public interface IMinhaCarteiraDao {

	public void addCoin(Carteira m) throws ClassNotFoundException, SQLException;
	public void removeCoin(Carteira m) throws ClassNotFoundException, SQLException;
	public ArrayList<Carteira> selectCarteira() throws ClassNotFoundException, SQLException, ParseException;
}
