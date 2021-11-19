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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
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
	
	JSONObject jsonData ;
	
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
    private TextField tFieldVenda;

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
    void atualizarCarteira(ActionEvent event) throws ClassNotFoundException, SQLException {
    	
    	if(moedaCarteira == null) {
    		JOptionPane.showMessageDialog(null, "Favor selecionar uma moeda para vender!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (tFieldVenda.getText().isEmpty()) {
    		JOptionPane.showMessageDialog(null, "Favor informar uma quantidade!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if (util.isNumber(tFieldVenda.getText()) == false || util.toDouble(tFieldVenda.getText()) <= 0) {
    		JOptionPane.showMessageDialog(null, "Favor informar uma quantidade válida!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else {
    		try {
    			if(util.toDouble(moedaCarteira.getQuantidade()) < util.toDouble(tFieldVenda.getText())) {
        			JOptionPane.showMessageDialog(null, "Quantidade selecionada acima da quantidade em carteira!", "ERRO", JOptionPane.ERROR_MESSAGE);
            	} else if (util.toDouble(moedaCarteira.getQuantidade()) > util.toDouble(tFieldVenda.getText())) {
            		MinhaCarteiraController cart = new MinhaCarteiraController();
            		Carteira c = new Carteira();
            		
            		double result = (util.toDouble(moedaCarteira.getQuantidade()) - (util.toDouble(tFieldVenda.getText())));
            		
            		c.setQuantidade(util.DoubleOitoCasasDecimais(result));
            		c.setIdCarteira(moedaCarteira.getIdCarteira());
            		
            		cart.updateCoin(c);   
            		
            		JOptionPane.showMessageDialog(null, "Quantidade vendidida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            		AtualizarBase();
            	} else {
            		MinhaCarteiraController cart = new MinhaCarteiraController();
            		cart.removeCoin(moedaCarteira);
            		JOptionPane.showMessageDialog(null, "Quantidade vendidida com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            		AtualizarBase();
            	}
    		} catch(Exception e) {
    			JOptionPane.showMessageDialog(null, "Erro" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
    		}
    	}	   	
    }
    
    @FXML
    void textChange(KeyEvent event) {
    	lblDescricao.setText("Favor informar a quantidade a ser vendida abaixo abaixo!");
    	if(!tFieldVenda.getText().isEmpty() && !(moedaCarteira == null)) {
    		if(util.isNumber(tFieldVenda.getText()) && util.toDouble(tFieldVenda.getText()) > 0) {
    			lblDescricao.setText("Você esta realizando a venda de " + util.DoubleOitoCasasDecimais(tFieldVenda.getText()) + " " + moedaCarteira.toStringSemData());
    			if(CalcularValor() > 0) {
    				lblOperacao.setText("Ao realizar a venda terá o lucro de " + CalcularValor() + " Reais");
    			} else if (CalcularValor() < 0){
    				lblOperacao.setText("Ao realizar a venda terá o prejuizo de " + (CalcularValor() * -1) + " Reais");
    			} else {
    				lblOperacao.setText("Da na mesma!");
    			} 
        	} else { 
        		lblDescricao.setText("Favor informar uma quantidade válida para vender!");
        		lblOperacao.setText("");
        	}
    	} else if(moedaCarteira == null) {
    		lblDescricao.setText("Favor informar uma moeda acima!");
    	} else if (tFieldVenda.getText().isEmpty()) {
    		lblDescricao.setText("Favor informar a quantidade a ser vendida abaixo!");
    		lblOperacao.setText("");
    	}
    	else {
    		lblDescricao.setText("");
    		lblOperacao.setText("");
    	}
    }

    @FXML
    void chamarApi(ActionEvent event) throws ParseException {
    	ApiConnect api = new ApiConnect();
    	
    	moedaCarteira = cBoxMoeda.getValue();
    	
    	if(moedaCarteira != null) {
    		jsonData = (JSONObject) api.getJsonObj(apiMoeda, moedaCarteira.getCodeApi() + "/ticker").get("ticker");
        	
        	moedaCarteira.setValorVenda(jsonData.get("sell").toString());
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
    
    public void initialize() throws ClassNotFoundException, SQLException, ParseException {
    	lblUser.setText("Seja bem vindo(a), " + UserLogado.fulano());
    	AtualizarBase();
    }
    
    public void AtualizarBase() throws ClassNotFoundException, SQLException, ParseException {
		limparCampos();
    	MinhaCarteiraController cart = new MinhaCarteiraController();
		
    	listaMoedas = cart.selectCarteira();
    	ObservableList<Carteira> listaDeMoedas = FXCollections.observableList(listaMoedas);
		cBoxMoeda.setItems(listaDeMoedas);
    	
		api = new ApiConnect();
		moedaApi = new ApiMoeda();
    }
    
    public Double CalcularValor() {
    	Double valor1 = util.toDouble(lblValorReal.getText().substring(2)) * util.toDouble(tFieldVenda.getText());
    	Double valor2 = util.toDouble(moedaCarteira.getValorCompra()) * util.toDouble(tFieldVenda.getText());
    	Double result = (valor1 - valor2);
    	return util.toDouble(util.DoubleDuasCasasDecimais(result));
    }
    
    public void showCampos() {
    	pane1.setVisible(true);
	 	pane2.setVisible(true);
	 	pane3.setVisible(true);
	 	pane4.setVisible(true);
	 	pane5.setVisible(true);
	 	btnMoeda.setVisible(true);
	 	tFieldVenda.setVisible(true);
    	lblValorReal.setText("R$ " +  util.DoubleQuatroCasasDecimais(Double.parseDouble(moedaCarteira.getValorVenda().replace(",", "."))));
    	lblDataAtual.setText(moedaApi.getData());
    	lblQtdCarteira.setText(util.DoubleOitoCasasDecimais(Double.parseDouble(moedaCarteira.getQuantidade().replace(",", "."))));
    	lblDataCompra.setText(moedaCarteira.getData());
    	lblValorPago.setText("R$ " + util.DoubleQuatroCasasDecimais(Double.parseDouble(moedaCarteira.getValorCompra().replace(",", "."))));
    }
    
    public void limparCampos() {
	 	lblDescricao.setText("");
	 	lblOperacao.setText("");
	 	lblValorReal.setText("");
	 	lblDataAtual.setText("");
	 	lblQtdCarteira.setText("");
	 	lblDataCompra.setText("");
	 	lblValorPago.setText("");
	 	tFieldVenda.setText("");
	 	pane1.setVisible(false);
	 	pane2.setVisible(false);
	 	pane3.setVisible(false);
	 	pane4.setVisible(false);
	 	pane5.setVisible(false);
	 	btnMoeda.setVisible(false);
	 	tFieldVenda.setVisible(false);
    }
}
