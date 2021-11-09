package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import db.DBConnection;
import entity.Carteira;
import util.UserLogado;

public class MinhaCarteiraDao implements IMinhaCarteiraDao{

	private Connection c;

	public MinhaCarteiraDao() throws ClassNotFoundException, SQLException {
		
		DBConnection cDao = new DBConnection();
		c = cDao.getConnection();
	}
	
	@Override
	public void addCoin(Carteira m) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Tb_Carteira VALUES(?,?,?,?,?)";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, m.getId());
		ps.setLong(2, UserLogado.getUserId());
		ps.setString(3, m.getValorCompra());
		ps.setString(4, m.getData());
		ps.setString(5, m.getQuantidade());

		ps.execute();
		ps.close();
		
	}

	@Override
	public void removeCoin(Carteira m) throws ClassNotFoundException, SQLException {
		String sql = "DELETE FROM Tb_Carteira WHERE id = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, m.getIdCarteira());
		
		ps.execute();
		ps.close();
	}

	@Override
	public ArrayList<Carteira> selectCarteira() throws ClassNotFoundException, SQLException, ParseException {
		ArrayList<Carteira> listaCarteira = new ArrayList<Carteira>();
		Carteira cart;
		
		String sql = "SELECT c.descricao, c.codeApi, ct.valor, ct.quantidade, ct.dataCompra "
				+ "FROM Tb_Carteira ct "
				+ "INNER JOIN Coin c ON  "
				+ "c.id = ct.idCoin  "
				+ "INNER JOIN Tb_Usuario u ON u.id = ct.idUser "
				+ "WHERE idUser = ?"
				+ "ORDER BY ct.dataCompra ASC";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, UserLogado.getUserId());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			cart = new Carteira();
			
			cart.setDescricao(rs.getString("descricao"));
			cart.setValorCompra(rs.getString("valor"));
			cart.setQuantidade(rs.getString("quantidade"));
			cart.setDataFormatada(rs.getDate("dataCompra").toString());
			cart.setCodeApi(rs.getString("codeApi"));
			
			listaCarteira.add(cart);
		}
		
		return listaCarteira;
	}
}
