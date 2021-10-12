package entity;

public class Moeda {

	private Long id;
    private String descricao;
    private String codeApi;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getCodeApi() {
		return codeApi;
	}
	
	public void setCodeApi(String codeApi) {
		this.codeApi = codeApi;
	}

	@Override
	public String toString() {
		String text = descricao.substring(0,1).toUpperCase().concat(descricao.substring(1));
		return text;
	}
}
