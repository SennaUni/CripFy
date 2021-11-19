package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.swing.JOptionPane;

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
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import util.SetPages;
import util.util;
import util.UserLogado;

public class SimularCompraFxml {

	protected String apiMoeda = "https://www.mercadobitcoin.net/api/";
	protected String apiDolar = "https://economia.awesomeapi.com.br/last/";
	
	ApiConnect api;
	Moeda moeda;
	ApiMoeda moedaApi;
	Carteira moedaCarteira;
	List<Moeda> listaMoedas;

	JSONObject jsonData;
	JSONObject jsonDataDolar;
	
    @FXML
    private Label lblUser;

    @FXML
    private ComboBox<Moeda> cBoxMoeda;
    
    @FXML
    private Button btnMoeda;

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
    private Pane pane1;
    
    @FXML
    private Pane pane2;
    
    @FXML
    private Pane pane3;
    
    @FXML
    private Pane pane4;
    
    @FXML
    private Pane pane5;

    @FXML
    void adicionarMoeda(ActionEvent event) {
    	MinhaCarteiraController mcc = new MinhaCarteiraController();
    	
    	if(cBoxMoeda.getValue() == null) {
    		JOptionPane.showMessageDialog(null, "Favor selecionar uma moeda para adicionar!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (tFieldQtd.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Favor informar um investimento!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (util.isNumber(tFieldQtd.getText()) == false || util.toDouble(tFieldQtd.getText()) <= 0) {
    		JOptionPane.showMessageDialog(null, "Favor informar um valor válido para investir!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else {
    		try {  
    			moedaCarteira = new Carteira();
        		
    			moedaCarteira.setId(moeda.getId());
    			moedaCarteira.setIdUser(UserLogado.getUserId());
    			moedaCarteira.setValorCompra(moedaApi.getValorCompra());
    			moedaCarteira.setDataFormatada(moedaApi.getData());
    			moedaCarteira.setQuantidade(CalcularFracao(moedaApi.getValorCompra().toString(), tFieldQtd.getText()));

        		mcc.addCoin(moedaCarteira);

    			JOptionPane.showMessageDialog(null, "Moeda adicionada a carteira com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    			limparCampos();
    			
        	} catch (Exception e) {
        		JOptionPane.showMessageDialog(null, "Erro" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        	}
    	}
    }

    @FXML
    void textChange(KeyEvent event) {
    	lblTitulo.setText("Favor informar o seu investimento abaixo!");
    	if(!tFieldQtd.getText().isEmpty() && !(moeda == null)) {
    		if(util.isNumber(tFieldQtd.getText()) && util.toDouble(tFieldQtd.getText()) > 0) {
    			lblTitulo.setText("Você esta realizando a compra de " + CalcularFracao(moedaApi.getValorCompra().toString(), tFieldQtd.getText()) + " " + moeda.toString());
        		lblDescricao.setText("Valor investido: R$" + tFieldQtd.getText().toString() + " Reais");
        	} else {
        		lblTitulo.setText("Favor informar um valor válido para investimento!");
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
   	
    	if(moeda != null) {
        	jsonData = (JSONObject) api.getJsonObj(apiMoeda, moeda.getCodeApi() + "/ticker").get("ticker");
        	
        	jsonDataDolar = (JSONObject) api.getJsonObj(apiDolar, "USD-BRL").get("USDBRL");
        	
        	moedaApi.setValorCompra(jsonData.get("buy").toString());
        	moedaApi.setData(jsonData.get("date").toString());
        	
        	showCampos();
    	}
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
    
    public void initialize() throws ClassNotFoundException, SQLException {
		limparCampos();
    	lblUser.setText("Seja bem vindo(a), " + UserLogado.fulano());
    	
    	MoedaController moeda = new MoedaController();
		
    	listaMoedas = moeda.selectCoins();
    	ObservableList<Moeda> listaDeMoedas = FXCollections.observableList(listaMoedas);
		cBoxMoeda.setItems(listaDeMoedas);
    	
		api = new ApiConnect();
		moedaApi = new ApiMoeda();
    }
    
    public String CalcularFracao(String total, String investido) {
    	
    	Double result = util.toDouble(investido) / util.toDouble(total);
    	
    	return util.DoubleOitoCasasDecimais(result);
    }
    
    public void showCampos() {
    	pane1.setVisible(true);
	 	pane2.setVisible(true);
	 	pane3.setVisible(true);
	 	pane4.setVisible(true);
	 	pane5.setVisible(true);
	 	btnMoeda.setVisible(true);
	 	tFieldQtd.setVisible(true);
    	lblValorReal.setText("$ " +  moedaApi.getValorCompra());
    	lblDataCompra.setText(moedaApi.getData());
    	lblValorDolar.setText("R$ " + util.convertDolarToReal(jsonDataDolar.get("bid").toString(), moedaApi.getValorCompra()));
    	lblCotacaoDolar.setText("R$ " + jsonDataDolar.get("bid").toString());
    	lblQtdCompra.setText(util.DoubleQuatroCasasDecimais(Double.parseDouble(jsonData.get("vol").toString())).toString());
    }
    
    public void limparCampos() {
    		cBoxMoeda.setValue(null);
    	 	lblValorDolar.setText("");
    	 	lblValorReal.setText("");
    	    lblDataCompra.setText("");
    	    lblQtdCompra.setText("");
    	    lblCotacaoDolar.setText("");
    	    lblTitulo.setText("");
    	    lblDescricao.setText("");
    	    pane1.setVisible(false);
    	 	pane2.setVisible(false);
    	 	pane3.setVisible(false);
    	 	pane4.setVisible(false);
    	 	pane5.setVisible(false);
    	 	btnMoeda.setVisible(false);
    	 	tFieldQtd.setVisible(false);
    }
}
