package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.Moeda;

public class MoedaDao implements IMoedaDao{

	private Connection c;
	
	public MoedaDao() throws ClassNotFoundException, SQLException {
		
		DBConnection cDao = new DBConnection();
		c = cDao.getConnection();
	}
	@Override
	public ArrayList<Moeda> selectCoins() throws ClassNotFoundException, SQLException {
		
		ArrayList<Moeda> listaDeMoedas = new ArrayList<Moeda>();
		Moeda coin;

		String sql = "SELECT * FROM Coin";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		while(rs.next()) {
			coin = new Moeda();
			
			coin.setId(rs.getLong("id"));
			coin.setDescricao(rs.getString("descricao"));
			coin.setCodeApi(rs.getString("codeApi"));
			
			listaDeMoedas.add(coin);
		}
		
		return listaDeMoedas;
	}

}
