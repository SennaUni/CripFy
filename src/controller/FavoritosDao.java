package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import util.UserLogado;

import db.DBConnection;
import entity.Favorito;
import entity.Moeda;

public class FavoritosDao implements IFavoritosDao{
	
	private Connection c;

	public FavoritosDao() throws ClassNotFoundException, SQLException {
		
		DBConnection cDao = new DBConnection();
		c = cDao.getConnection();
	}

	@Override
	public void addCoin(Moeda m) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Tb_Favorito VALUES(?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, UserLogado.getUserId());
		ps.setLong(2, m.getId());
		
		ps.execute();
		ps.close();
	}
	
	@Override
	public void removeCoin(Favorito fav) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Tb_Favorito WHERE id = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, fav.getId());
		
		ps.execute();
		ps.close();
	}

	@Override
	public ArrayList<Favorito> selectFav() throws ClassNotFoundException, SQLException {
		
		ArrayList<Favorito> listaFavoritos = new ArrayList<Favorito>();
		Favorito fav;
		
		String sql = "SELECT f.id, f.idCoin, c.descricao, c.codeApi "
				+ "FROM Tb_Favorito f "
				+ "INNER JOIN Coin c ON "
				+ "c.id = f.idCoin "
				+ "INNER JOIN Tb_Usuario u ON "
				+ "u.id = f.idUser "
				+ "WHERE idUser = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, UserLogado.getUserId());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			fav = new Favorito();
			
			fav.setId(rs.getInt("id"));
			fav.setIdCoin(rs.getInt("idCoin"));
			fav.setDescricaoCoin(rs.getString("descricao"));
			fav.setCodeApi(rs.getString("codeApi"));
			
			listaFavoritos.add(fav);
		}
		
		return listaFavoritos;
	}

}
