package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBConnection;
import entity.ApiMoeda;
import entity.Favorito;
import util.UserLogado;

public class PrincipalDao implements IPrincipalDao{

	private Connection c;

	public PrincipalDao() throws ClassNotFoundException, SQLException {
		
		DBConnection cDao = new DBConnection();
		c = cDao.getConnection();
	}
	
	@Override
	public ArrayList<ApiMoeda> selectMoedas() throws ClassNotFoundException, SQLException {
		ArrayList<ApiMoeda> listaMoedas = new ArrayList<ApiMoeda>();
		ApiMoeda m;
		
		String sql = "select c.id, c.descricao, c.codeApi "
				+ "from Tb_Favorito f "
				+ "inner join Coin c on "
				+ "f.idCoin = c.id "
				+ "where f.idUser = ?";
		
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setLong(1, UserLogado.getUserId());
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			m = new ApiMoeda();
	
			m.setId(rs.getLong("id"));
			m.setDescricao(rs.getString("descricao"));
			m.setCodeApi(rs.getString("codeApi"));
			
			listaMoedas.add(m);
		}
		
		return listaMoedas;
	}

}
