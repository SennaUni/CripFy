package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.SetPages;

public class SimularVendaFxml {

    @FXML
    private Label lblFavoritos1;

    @FXML
    private Label lblFavoritos;

    @FXML
    private Label lblCarteira;

    @FXML
    private Label lblSimularCompra;

    @FXML
    private Label lblUser;

    @FXML
    private ComboBox<?> cBoxMoeda;

    @FXML
    private Button btnMoeda;

    @FXML
    private TextField tFieldCompra;

    @FXML
    private Label lblValorDolar;

    @FXML
    private Label lblValorReal;

    @FXML
    private Label lblDataAtual;

    @FXML
    private Label lblQtdCarteira;

    @FXML
    private Label lblDataCompra;

    @FXML
    private Label lblValorPago;

    @FXML
    private Label lblDescricao;

    @FXML
    private Label lblOperacao;

    @FXML
    void adicionarMoeda(ActionEvent event) {
    	
    }

    @FXML
    void clickDashboard(MouseEvent event) throws IOException {
    	SetPages.HomePage(event);
    }

    @FXML
    void clickFavoritos(MouseEvent event) throws IOException {
    	SetPages.FavPage(event);
    }

    @FXML
    void clickMinhaCarteira(MouseEvent event) {

    }

    @FXML
    void clickMinhaConta(MouseEvent event) throws IOException {
    	SetPages.PerfilPage(event);
    }

    @FXML
    void clickSimularCompra(MouseEvent event) throws IOException {
    	SetPages.CompraPage(event);
    }

    @FXML
    void clickSimularVenda(MouseEvent event) throws IOException {
    	SetPages.VendaPage(event);
    }

}