package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import org.json.simple.JSONObject;

import entity.ApiConnect;
import entity.ApiMoeda;
import entity.Moeda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import util.SetPages;
import util.util;
import util.UserLogado;

public class SimularCompraFxml {

	protected String apiMoeda = "https://www.mercadobitcoin.net/api/";
	protected String apiDolar = "https://economia.awesomeapi.com.br/last/";
	
	ApiConnect api;
	Moeda moeda;
	ApiMoeda moedaApi;
	List<Moeda> listaMoedas;

    @FXML
    private Label lblUser;

    @FXML
    private ComboBox<Moeda> cBoxMoeda;

    @FXML
    private TextField tFieldQtd;
    
    @FXML
    private Label lblValorDolar;

    @FXML
    private Label lblValorReal;

    @FXML
    private Label lblDataCompra;

    @FXML
    private Label lblQtdCompra;

    @FXML
    private Label lblCotacaoDolar;

    @FXML
    private Label lblTitulo;

    @FXML
    private Label lblDescricao;

    @FXML
    void adicionarMoeda(ActionEvent event) {
    	
    }

    @FXML
    void textChange(KeyEvent event) {
    	if(!tFieldQtd.getText().isEmpty() && !(moeda == null)) {
    		if(util.isNumber(tFieldQtd.getText())) {
    			lblTitulo.setText("Você esta realizando a compra de " + CalcularFracao(moedaApi.getValorCompra().toString(), tFieldQtd.getText()) + " " + moeda.toString());
        		lblDescricao.setText("Valor investido: R$" + tFieldQtd.getText().toString() + " Reais");
        	} else {
        		lblTitulo.setText("Favor informar somente números no input abaixo!");
        		lblDescricao.setText("");
        	}
    	} else if(moeda == null) {
    		lblTitulo.setText("Favor informar uma moeda acima!");
    	} else if (tFieldQtd.getText().isEmpty()) {
    		lblTitulo.setText("Favor informar o seu investimento abaixo!");
    		lblDescricao.setText("");
    	}
    	else {
    		lblTitulo.setText("");
    		lblDescricao.setText("");
    	}
    }
    
    @FXML
    void ChamarApi(ActionEvent event) throws ParseException {
    	ApiConnect api = new ApiConnect();
    	
    	moeda = cBoxMoeda.getValue();
   	
    	JSONObject jsonData = (JSONObject) api.getJsonObj(apiMoeda, moeda.getCodeApi() + "/ticker").get("ticker");
    	
    	JSONObject jsonDataDolar = (JSONObject) api.getJsonObj(apiDolar, "USD-BRL").get("USDBRL");
    	
    	moedaApi.setValorCompra(jsonData.get("buy").toString());
    	moedaApi.setData(jsonData.get("date").toString());
    	
    	lblValorReal.setText("$ " +  moedaApi.getValorCompra());
    	lblDataCompra.setText(moedaApi.getData());
    	lblValorDolar.setText("R$ " + util.convertDolarToReal(jsonDataDolar.get("bid").toString(), moedaApi.getValorCompra()));
    	lblCotacaoDolar.setText("R$ " + jsonDataDolar.get("bid").toString());
    	lblQtdCompra.setText(util.DoubleQuatroCadasDecimais(Double.parseDouble(jsonData.get("vol").toString())).toString());
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
    
    public void initialize() throws ClassNotFoundException, SQLException {
    	MoedaController moeda = new MoedaController();
		
    	listaMoedas = moeda.selectCoins();
    	ObservableList<Moeda> listaDeMoedas = FXCollections.observableList(listaMoedas);
    	
    	lblUser.setText("Seja bem vindo(a), " + UserLogado.fulano());
		limparCampos();
    	
		cBoxMoeda.setItems(listaDeMoedas);
    	
		api = new ApiConnect();
		moedaApi = new ApiMoeda();
    }
    
    public String CalcularFracao(String total, String investido) {
    	
    	Double result = Double.parseDouble(investido.replace(",", ".")) / Double.parseDouble(total.replace(",", "."));
    	
    	return util.DoubleQuatroCadasDecimais(result).toString();
    }
    
    public void limparCampos() {
    	 	lblValorDolar.setText("");
    	 	lblValorReal.setText("");
    	    lblDataCompra.setText("");
    	    lblQtdCompra.setText("");
    	    lblCotacaoDolar.setText("");
    	    lblTitulo.setText("");
    	    lblDescricao.setText("");
    }
}
