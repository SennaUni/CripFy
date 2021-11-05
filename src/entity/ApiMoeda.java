package entity;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

	public String getValorCompra() {
		return valorCompra;
	}

	public void setValorCompra(String valorCompra) {
		this.valorCompra = util.DoubleQuatroCadasDecimais(Double.parseDouble(valorCompra));
	}

	public String getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(String valorVenda) {
		this.valorVenda = util.DoubleQuatroCadasDecimais(Double.parseDouble(valorVenda));
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
		return "ApiMoeda [descricao = " + getDescricao() + " maiorValor=" + maiorValor + ", menorValor=" + menorValor + "]";
	}
}
