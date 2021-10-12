package controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class PrincipalFxml {

    @FXML
    void clickCarteira(MouseEvent event) {
    	
    }

    @FXML
    void clickFavoritos(MouseEvent event) throws IOException {
    	util.SetPages.FavPage(event);
    }

    @FXML
    void clickSimularCompra(MouseEvent event) {

    }

    @FXML
    void clickSimularVenda(MouseEvent event) {

    }
}
