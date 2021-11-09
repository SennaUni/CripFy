package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import entity.Moeda;
import entity.ApiConnect;
import entity.ApiMoeda;

public class MoedaController implements IMoedaController{

	@Override
	public ArrayList<Moeda> selectCoins() throws ClassNotFoundException, SQLException {

		ArrayList<Moeda> listaDeMoedas = new ArrayList<Moeda>();

		MoedaDao mDao = new MoedaDao();

		listaDeMoedas = mDao.selectCoins();

		return listaDeMoedas;

	}
}
