package entity;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import util.util;

public class ApiMoeda extends Moeda{

    private String maiorValor;
    private String menorValor;
    private String valorCompra;
    private String valorVenda;
	private String data;
	
    public String getData() {
		return data.toString();
	}

	public void setData(String data) throws ParseException {
		Date date = new java.util.Date(Long.parseLong(data) *1000L); 
		SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd.MM.yyyy");
		String formattedDate = sdf.format(date);
		this.data = formattedDate;
	}
	
	public void setDataFormatada(String data) throws ParseException {
		this.data = data;
	}

	public String getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(String valorCompra) {
		this.valorCompra = util.DoubleQuatroCasasDecimais(Double.parseDouble(valorCompra.replace(",", ".")));
	}

	public String getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(String valorVenda) {
		this.valorVenda = util.DoubleQuatroCasasDecimais(Double.parseDouble(valorVenda.replace(",", ".")));
	}

	public String getMaiorValor() {
			return maiorValor;
	}

	public void setMaiorValor(String maiorValor) {
		this.maiorValor = maiorValor;
	}

	public String getMenorValor() {
		return menorValor;
	}

	public void setMenorValor(String menorValor) {
		this.menorValor = menorValor;
	}
	    
	@Override
	public String toString() {
		String text = descricao.substring(0,1).toUpperCase().concat(descricao.substring(1)) + " - " + data.toString();
		return text;
	}
}
