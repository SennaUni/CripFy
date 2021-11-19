package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.ApiMoeda;
import entity.Favorito;
import entity.Moeda;

public class PrincipalController implements IPrincipalController{

	@Override
	public ArrayList<ApiMoeda> selectMoedas() throws ClassNotFoundException, SQLException {
		ArrayList<ApiMoeda> listaMoedas = new ArrayList<ApiMoeda>();
		
		PrincipalDao pDao = new PrincipalDao();
		
		listaMoedas = pDao.selectMoedas();
		
		return listaMoedas;
	}
}
