package entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import util.util;

public class ApiMoeda extends Moeda{

    private String maiorValor;
    private String menorValor;
    private String quantidadeNegociada;
    private String ultimoValor;
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
		this.valorCompra = util.DoubleQuatroCasasDecimais(valorCompra);
	}
	
	public void setValorCompraSifrao(String valorCompra) {
		this.valorCompra = valorCompra;
	}

	public String getValorVenda() {
		return valorVenda;
	}

	public void setValorVenda(String valorVenda) {
		this.valorVenda = util.DoubleQuatroCasasDecimais(valorVenda);
	}
	
	public void setValorVendaSifrao(String valorVenda) {
		this.valorVenda = valorVenda;
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
		String text;
		if(data == null) {
			text = descricao.substring(0,1).toUpperCase().concat(descricao.substring(1));	
		} else {
			text = descricao.substring(0,1).toUpperCase().concat(descricao.substring(1)) + " - " + data.toString();
		}
		return text;
	}

	public String getQuantidadeNegociada() {
		return quantidadeNegociada;
	}

	public void setQuantidadeNegociada(String quantidadeNegociada) {
		this.quantidadeNegociada = quantidadeNegociada;
	}

	public String getUltimoValor() {
		return ultimoValor;
	}

	public void setUltimoValor(String ultimoValor) {
		this.ultimoValor = ultimoValor;
	}
}
