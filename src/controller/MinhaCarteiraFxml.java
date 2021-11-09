package controller;

import java.awt.HeadlessException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import entity.Carteira;
import entity.Favorito;
import entity.Moeda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import util.SetPages;
import util.UserLogado;
import util.util;

public class MinhaCarteiraFxml {

	List<Moeda> listaMoedas;
	List<Carteira> listaCarteira;
	
	MoedaController m;
	Moeda moeda;
	Carteira moedaCarteira;
	
    @FXML
    private Label lblFavoritos1;

    @FXML
    private Label lblFavoritos;

    @FXML
    private Label lblCarteira;

    @FXML
    private Label lblSimularCompra;

    @FXML
    private Label lblSimularVenda;

    @FXML
    private TableView<Carteira> tbMoedas;

    @FXML
    private TableColumn<Carteira, String> descrCoin;

    @FXML
    private TableColumn<Carteira, String> valor;

    @FXML
    private TableColumn<Carteira, String> quantidade;

    @FXML
    private TableColumn<Carteira, String> dtCompra;

    @FXML
    private TableColumn<Carteira, Button> remover;

    @FXML
    private TextField tFieldValor;

    @FXML
    private DatePicker DatePicker;
    
    @FXML
    private Button btnMoeda;

    @FXML
    private ComboBox<Moeda> cBoxMoeda;

    @FXML
    private TextField tFieldQuantidade;

    @FXML
    private Label lblUser;

    @FXML
    void adicionarMoeda(ActionEvent event) throws HeadlessException, ParseException {
    	MinhaCarteiraController mcc = new MinhaCarteiraController();
    	
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    	LocalDate dateNow = LocalDate.now();
    	LocalDate datePicker = (DatePicker.getValue() != null) ? (LocalDate.parse(DatePicker.getValue().toString(), formatter)) : null;
		
    	if(cBoxMoeda.getValue() == null) {
    		JOptionPane.showMessageDialog(null, "Favor selecionar uma moeda para adicionar!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(tFieldValor.getText().isEmpty() || tFieldQuantidade.getText().isEmpty() || DatePicker.getValue() == null) {
    		JOptionPane.showMessageDialog(null, "Todos os campos são obrigatórios!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(util.isNumber(tFieldValor.getText().replace(",", ".")) == false || Double.parseDouble(tFieldValor.getText().replace(",", ".")) <= 0) {
    		JOptionPane.showMessageDialog(null, "Favor informar um valor válido!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(util.isNumber(tFieldQuantidade.getText().replace(",", ".")) == false || Double.parseDouble(tFieldQuantidade.getText().replace(",", ".")) <= 0) {
    		JOptionPane.showMessageDialog(null, "Favor informar uma quantidade válida!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else if(dateNow.isBefore(datePicker)) {
    		JOptionPane.showMessageDialog(null, "Favor informar uma data válida!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	}else {
    		try {  
    			moeda = cBoxMoeda.getValue();
    			moedaCarteira = new Carteira();
        		
    			moedaCarteira.setId(moeda.getId());
    			moedaCarteira.setIdUser(UserLogado.getUserId());
    			moedaCarteira.setValorCompra(util.DoubleOitoCasasDecimais(Double.parseDouble(tFieldValor.getText().replace(",", "."))));
    			moedaCarteira.setDataFormatada(datePicker.toString());
    			moedaCarteira.setQuantidade(util.DoubleOitoCasasDecimais(Double.parseDouble(tFieldQuantidade.getText().replace(",", "."))));

        		mcc.addCoin(moedaCarteira);

    			JOptionPane.showMessageDialog(null, "Moeda adicionada a carteira com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        		
        	} catch (Exception e) {
        		JOptionPane.showMessageDialog(null, "Erro" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
        	}
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

    public void initialize() {
    	lblUser.setText("Seja bem vindo(a), " + UserLogado.fulano());
    	m = new MoedaController();
		
    	try {

			listaMoedas = m.selectCoins();
			ObservableList<Moeda> listaDeMoedas = FXCollections.observableList(listaMoedas);
			cBoxMoeda.setItems(listaDeMoedas);
			atualizarTabela();
			
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
    	
	}
    
    
	void atualizarTabela() {
	    	
	    	MinhaCarteiraController cart = new MinhaCarteiraController();
	    	
	    	try {
	
	    		listaCarteira = cart.selectCarteira();
	
	    		for(Carteira c : listaCarteira) {
	    			Button remover = new Button();
	    			
	    			remover.setText("Remover");
	    			remover.setAlignment(Pos.CENTER);
	    			remover.setTextFill(Color.WHITE);
	    			remover.setStyle("-fx-background-color:  #33cccc; ");
	    			remover.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
	
						@Override
						public void handle(ActionEvent arg0) {
		
							if(JOptionPane.showConfirmDialog(null, "Deseja realmente remover?", "Calma lá amigao!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
								
								try {
									cart.removeCoin(c);
									JOptionPane.showMessageDialog(null, "Favorito removido com sucesso!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
									atualizarTabela();
								} catch (Exception e) {
									JOptionPane.showMessageDialog(null, "Ops ocorreu um erro inesperado!" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
								}
							}
						}
	
	    			});
	    			
	    			c.setButtonCarteira(remover);
	    		}
	    		
	    		ObservableList<Carteira> listaDeCarteiras = FXCollections.observableList(listaCarteira);
	    				    		
	    		descrCoin.setCellValueFactory(new PropertyValueFactory<Carteira, String>("descricao"));
	    		valor.setCellValueFactory(new PropertyValueFactory<Carteira, String>("valorCompra"));
	    		quantidade.setCellValueFactory(new PropertyValueFactory<Carteira, String>("quantidade"));
	    		dtCompra.setCellValueFactory(new PropertyValueFactory<Carteira, String>("data"));
	    		remover.setCellValueFactory(new PropertyValueFactory<Carteira, Button>("buttonCarteira"));
	    		
	    		tbMoedas.setItems(listaDeCarteiras);
	        	
	    	} catch (Exception e) {
	    		JOptionPane.showMessageDialog(null, "ComboBox com probleminhas" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
	    	}
	    } 
	} 
