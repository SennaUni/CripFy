package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.json.simple.JSONObject;

import entity.ApiConnect;
import entity.ApiMoeda;
import entity.Carteira;
import entity.Moeda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import util.SetPages;
import util.UserLogado;
import util.util;

public class SimularVendaFxml {

	protected String apiMoeda = "https://www.mercadobitcoin.net/api/";
	protected String apiDolar = "https://economia.awesomeapi.com.br/last/";
	
	ApiConnect api;
	ApiMoeda moedaApi;
	Carteira moedaCarteira;
	List<Carteira> listaMoedas;
	
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
    private ComboBox<Carteira> cBoxMoeda;

    @FXML
    private Button btnMoeda;

    @FXML
    private TextField tFieldCompra;

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
    void chamarApi(ActionEvent event) throws ParseException {
    	ApiConnect api = new ApiConnect();
    	
    	moedaCarteira = cBoxMoeda.getValue();
   	
    	JSONObject jsonData = (JSONObject) api.getJsonObj(apiMoeda, moedaCarteira.getCodeApi() + "/ticker").get("ticker");
    	
    	moedaCarteira.setValorVenda(jsonData.get("sell").toString());
    	moedaApi.setData(jsonData.get("date").toString());
    	
    	lblValorReal.setText("R$ " +  util.DoubleQuatroCasasDecimais(Double.parseDouble(moedaCarteira.getValorVenda().replace(",", "."))));
    	lblDataAtual.setText(moedaApi.getData());
    	lblQtdCarteira.setText(util.DoubleOitoCasasDecimais(Double.parseDouble(moedaCarteira.getQuantidade().replace(",", "."))));
    	lblDataCompra.setText(moedaCarteira.getData());
    	lblValorPago.setText("R$ " + util.DoubleQuatroCasasDecimais(Double.parseDouble(moedaCarteira.getValorCompra().replace(",", "."))));
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
    void clickMinhaCarteira(MouseEvent event) throws IOException {
    	SetPages.CarteiraPage(event);
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
    
    public void initialize() throws ClassNotFoundException, SQLException, ParseException {
    	limparCampos();
    	lblUser.setText("Seja bem vindo(a), " + UserLogado.fulano());
    	lblDescricao.setText("Favor informar o quantidade a ser vendida abaixo!");

    	MinhaCarteiraController cart = new MinhaCarteiraController();
		
    	listaMoedas = cart.selectCarteira();
    	ObservableList<Carteira> listaDeMoedas = FXCollections.observableList(listaMoedas);
		cBoxMoeda.setItems(listaDeMoedas);
    	
		api = new ApiConnect();
		moedaApi = new ApiMoeda();
    }
    
    public void limparCampos() {
	 	lblDescricao.setText("");
	 	lblOperacao.setText("");
	 	lblValorReal.setText("");
	 	lblDataAtual.setText("");
	 	lblQtdCarteira.setText("");
	 	lblDataCompra.setText("");
	 	lblValorPago.setText("");
    }
}
