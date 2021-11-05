package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import util.UserLogado;
import util.SetPages;

public class PrincipalFxml {

    @FXML
    private Label lblUser;
    

    @FXML
    void clickDashboard(MouseEvent event) throws IOException {
    	SetPages.HomePage(event);
    }
    
    @FXML
    void clickCarteira(MouseEvent event) {
    	
    }

    @FXML
    void clickFavoritos(MouseEvent event) throws IOException {
    	SetPages.FavPage(event);
    }
    

    @FXML
    void clickSimularCompra(MouseEvent event) throws IOException {
    	SetPages.CompraPage(event);
    }

    @FXML
    void clickSimularVenda(MouseEvent event) throws IOException {
    	SetPages.VendaPage(event);
    }
    
    @FXML
    void clickMinhaConta(MouseEvent event) throws IOException {
    	SetPages.PerfilPage(event);
    }
    
    public void initialize() {
    	lblUser.setText("Seja bem vindo(a), "+ UserLogado.getUserName());
	}
}
