package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import entity.ApiMoeda;

public interface IPrincipalDao {

	public ArrayList<ApiMoeda> selectMoedas() throws ClassNotFoundException, SQLException;
}
