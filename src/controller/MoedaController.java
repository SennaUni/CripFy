package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import entity.Moeda;
import entity.ApiConnect;
import entity.ApiMoeda;

public class MoedaController implements IMoedaController{

	@Override
	public Moeda getApiMoeda() {

		ApiConnect api = new ApiConnect("adsad");
		
		JSONObject jsonData = (JSONObject) api.getJsonObj().get("ticker");		
		
		ApiMoeda m = new ApiMoeda();
		
		m.setMaiorValor(jsonData.get("high").toString());
		m.setMenorValor(jsonData.get("low").toString());
		
		System.out.println(m);
		
		return null;
	}

	@Override
	public ArrayList<Moeda> selectCoins() throws ClassNotFoundException, SQLException {

		ArrayList<Moeda> listaDeMoedas = new ArrayList<Moeda>();

		MoedaDao mDao = new MoedaDao();

		listaDeMoedas = mDao.selectCoins();

		return listaDeMoedas;

	}
}
