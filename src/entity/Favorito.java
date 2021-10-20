package entity;

import javafx.scene.control.Button;

public class Favorito {
	
	private int id;
    private int idUser;
    private String nomeUser;
	private int idCoin;
    private String descricaoCoin;
    private String codeApi;
    private Button button;
    
    public int getId() {
		return id;
	}
    
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdUser() {
		return idUser;
	}
	
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	public String getNomeUser() {
		return nomeUser;
	}
	
	public void setNomeUser(String nomeUser) {
		this.nomeUser = nomeUser;
	}
	
	public int getIdCoin() {
		return idCoin;
	}
	
	public void setIdCoin(int i) {
		this.idCoin = i;
	}
	
	public String getDescricaoCoin() {
		return descricaoCoin;
	}
	
	public void setDescricaoCoin(String descricaoCoin) {
		this.descricaoCoin = descricaoCoin;
	}

	public String getCodeApi() {
		return codeApi;
	}

	public void setCodeApi(String codeApi) {
		this.codeApi = codeApi;
	}

	public Button getButton() {
		return button;
	}

	public void setButton(Button button) {
		this.button = button;
	}
}
