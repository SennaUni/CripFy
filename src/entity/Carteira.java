package entity;

import javafx.scene.control.Button;

public class Carteira extends ApiMoeda{

	private int idCarteira;
    private long idUser;
	private String quantidade;
	private Button buttonCarteira;

	public String getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(String string) {
		this.quantidade = string;
	}

	public long getIdUser() {
		return idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public int getIdCarteira() {
		return idCarteira;
	}

	public void setIdCarteira(int idCarteira) {
		this.idCarteira = idCarteira;
	}

	public Button getButtonCarteira() {
		return buttonCarteira;
	}

	public void setButtonCarteira(Button buttonCarteira) {
		this.buttonCarteira = buttonCarteira;
	}
	
	@Override
	public String toString() {
		String text = descricao.substring(0,1).toUpperCase().concat(descricao.substring(1)) + " - " + getData();
		return text;
	}

	public String toStringSemData() {
		String text = descricao.substring(0,1).toUpperCase().concat(descricao.substring(1));
		return text;
	}
}
