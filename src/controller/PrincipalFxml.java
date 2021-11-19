package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import entity.ApiConnect;
import entity.ApiMoeda;
import entity.Favorito;
import entity.Moeda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import util.SetPages;
import util.UserLogado;
import util.util;

public class PrincipalFxml {
	
	protected String apiMoeda = "https://www.mercadobitcoin.net/api/";
	
	ApiMoeda moeda = new ApiMoeda();
	List<ApiMoeda> listaMoedas;
	
	JSONObject jsonData;
	JSONArray jsonDataTrade;
	JSONObject jsonDay;
	
 	@FXML
    private Pane pane1;

    @FXML
    private Label lblValMin;

    @FXML
    private Pane pane2;

    @FXML
    private Label lblValAtual;

    @FXML
    private Pane pane3;

    @FXML
    private Label lblValMax;
    
    @FXML
    private Pane pane4;

    @FXML
    private Label lblMinNeg;

    @FXML
    private Pane pane5;

    @FXML
    private Label lblNegs;

    @FXML
    private Pane pane6;

    @FXML
    private Label lblMaxNeg;
    
    @FXML
    private ComboBox<ApiMoeda> cBoxMoeda;
    
    @FXML
    private PieChart grafico;
    
    @FXML
    private BarChart<String, Double> barChart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private Label lblUser;
    
    public void initialize() throws ClassNotFoundException, SQLException {
    	lblUser.setText("Seja bem vindo(a), " + UserLogado.fulano());
    	
    	PrincipalController p = new PrincipalController();
    	listaMoedas = p.selectMoedas();
    	
    	ObservableList<ApiMoeda> listaDeMoedas = FXCollections.observableList(listaMoedas);
    	cBoxMoeda.setItems(listaDeMoedas);
    	   
	}
    
    @FXML
    void ChamarApi(ActionEvent event) {
    	ApiConnect api = new ApiConnect();    	
    	grafico.getData().clear();
    	barChart.getData().clear();
    	
    	int sell = 0;
    	int buy = 0;
    	
    	int dia = LocalDate.now().getDayOfMonth() - 1;
    	int mes = LocalDate.now().getMonthValue();
    	int ano = LocalDate.now().getYear();
    	int contador = 0;
    	
    	moeda = cBoxMoeda.getValue();

    	if(moeda != null) {
        	jsonData = (JSONObject) api.getJsonObj(apiMoeda, moeda.getCodeApi() + "/ticker").get("ticker");
        	jsonDataTrade = (JSONArray) api.getJsonArray(apiMoeda, moeda.getCodeApi() + "/trades/");
        	
        	for(int i=0; i < jsonDataTrade.size(); i++) {
        		JSONObject aux = (JSONObject) jsonDataTrade.get(i);
        		
        		if(aux.get("type").equals("sell")) {
        			sell++;
        		} else {
        			buy++;
        		}
        	}
        	
        	grafico.getData().addAll(
      			  new PieChart.Data("Compra (" + (buy/10) + "%)", buy),
      	    	  new PieChart.Data("Venda (" + (sell/10) + "%)", sell)
      	    	);
  	    	grafico.setTitle("Relação Venda x Compra");
        
        	moeda.setMaiorValor("R$ " + util.DoubleQuatroCasasDecimais(jsonData.get("high").toString()));
			moeda.setMenorValor("R$ " + util.DoubleQuatroCasasDecimais(jsonData.get("low").toString()));
			moeda.setQuantidadeNegociada(util.DoubleOitoCasasDecimais(jsonData.get("vol").toString()));
			moeda.setUltimoValor("R$ " + util.DoubleQuatroCasasDecimais(jsonData.get("last").toString()));
			moeda.setValorCompraSifrao("R$ " + util.DoubleQuatroCasasDecimais(jsonData.get("buy").toString()));
			moeda.setValorVendaSifrao("R$ " + util.DoubleQuatroCasasDecimais(jsonData.get("sell").toString()));
			
			lblValMin.setText(moeda.getMenorValor());
			lblValAtual.setText(moeda.getUltimoValor());
			lblValMax.setText(moeda.getMaiorValor());
			lblMinNeg.setText(moeda.getValorVenda());
			lblNegs.setText(moeda.getQuantidadeNegociada());
			lblMaxNeg.setText(moeda.getValorCompra());
			    	

    		XYChart.Series<String, Double> set1 = new Series<String, Double>();

	    	for(int i=0; i < 12; i++) {
	    		
				 System.out.println(mes);
				 System.out.println(ano);
				 System.out.println("----");
	    		 String data = String.valueOf(mes) + "/" + String.valueOf(ano);
				 
				 try {
					 jsonDay = (JSONObject) api.getJsonObj(apiMoeda, moeda.getCodeApi() + "/day-summary/" + ano + "/" + mes + "/" + dia);   
			    		
					 if(jsonDay != null) {
						 contador++;
						 set1.getData().add(new Data<String, Double>(data, Double.parseDouble(jsonDay.get("avg_price").toString())));
				    		
			    		if(mes == 1) {
			    			mes = 12;
			    			ano--;
			    		} else {
			    			mes--;
			    		} 
					 } else {
						 break;
					 }
			    		
				 }catch (Exception e) {
					System.out.println(e);
				}
				 
	    		
        	}
	    	set1.setName("Valores dos ultimos " + contador + " meses");
	    	barChart.getData().add(set1);
		} 	
        	//showCampos();  	
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

}
