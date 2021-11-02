package entity;

public class ApiMoeda extends Moeda{

    private String maiorValor;
    private String menorValor;
	
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
