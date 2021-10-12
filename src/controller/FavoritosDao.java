package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.Moeda;

public class FavoritosDao implements IFavoritosDao{
	
	private Connection c;

	public FavoritosDao() throws ClassNotFoundException, SQLException {
		
		DBConnection cDao = new DBConnection();
		c = cDao.getConnection();
	}

	@Override
	public void addCoin(Moeda m) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Tb_Usuario VALUES(?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, m.getId());
		//ps.setId(2, util.userLogado.id);
		
		ps.execute();
		ps.close();
	}

	@Override
	public ArrayList<Moeda> selectAll() throws ClassNotFoundException, SQLException {
		
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
	
	@Override
	public void removeCoin(Moeda m) throws ClassNotFoundException, SQLException {
		
	}

}
