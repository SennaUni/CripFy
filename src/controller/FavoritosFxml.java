package controller;

import java.awt.TrayIcon.MessageType;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import entity.Favorito;
import entity.Moeda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import util.UserLogado;


public class FavoritosFxml {

	List<Moeda> listaMoedas;
	List<Favorito> listaFavoritos;
	
    @FXML
    private Label lblUser;
    
    @FXML
    private Label lblFavoritos;

    @FXML
    private Label lblCarteira;

    @FXML
    private Label lblSimularCompra;

    @FXML
    private Label lblSimularVenda;

    @FXML
    private ComboBox<Moeda> cBoxMoeda;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           

    @FXML
    private Button btnMoeda;

    @FXML
    private TableView<Favorito> tbMoedas;

    @FXML
    private TableColumn<Favorito, String> descrCoin;

    @FXML
    private TableColumn<Favorito, String> codeApi;

    @FXML
    private TableColumn<Favorito, Button> remove;


    public void initialize() {
    	//lblUser.setText("Seja bem vindo(a), " + UserLogado.getUserName());
    	
    	atualizarTabela();
	}
    
    @FXML
    void adicionarMoeda(ActionEvent event) {
    	FavoritosController fav = new FavoritosController();
    	
    	if(cBoxMoeda.getValue() == null) {
    		JOptionPane.showMessageDialog(null, "Favor selecionar uma moeda para adicionar!", "ERRO", JOptionPane.ERROR_MESSAGE);
    	} else {
    		try {
        		Moeda moedaName = cBoxMoeda.getValue();
        		
            	for(Moeda m : listaMoedas) {
            		if(moedaName.getDescricao() == m.getDescricao()) {
            			fav.addCoin(m);
            			JOptionPane.showMessageDialog(null, "Moeda adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            			atualizarTabela();
            			return;
            		}
            	}
        		
        	} catch (Exception e) {
        		JOptionPane.showMessageDialog(null, "Não é possível adicionar moedas iguais aos favoritos!", "ERRO", JOptionPane.ERROR_MESSAGE);
        	}
    	}

    }
    
    void atualizarTabela() {
    	
    	FavoritosController fav = new FavoritosController();
    	
    	try {
    		
    		listaMoedas = fav.selectCoins();
    		listaFavoritos = fav.selectFav();
    		
    		for(Favorito f : listaFavoritos) {
    			Button editar = new Button();
    			
    			editar.setText("Remover");
    			editar.setAlignment(Pos.CENTER);
    			editar.setTextFill(Color.WHITE);
    			editar.setStyle("-fx-background-color:  #33cccc; ");
    			editar.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

					@Override
					public void handle(ActionEvent arg0) {
	
						if(JOptionPane.showConfirmDialog(null, "Deseja realmente remover?", "Calma lá amigao!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
							
							try {
								fav.removeCoin(f);
								JOptionPane.showMessageDialog(null, "Favorito removido com sucesso!", "ERRO", JOptionPane.INFORMATION_MESSAGE);
								atualizarTabela();
							} catch (Exception e) {
								JOptionPane.showMessageDialog(null, "Ops ocorreu um erro inesperado!" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
							}
						}
					}

    			});
    			
    			f.setButton(editar);
    		}
    		
    		ObservableList<Moeda> listaDeMoedas = FXCollections.observableList(listaMoedas);
    		ObservableList<Favorito> listaDeFavoritos = FXCollections.observableList(listaFavoritos);
    				
    		cBoxMoeda.setItems(listaDeMoedas);
    		
    		descrCoin.setCellValueFactory(new PropertyValueFactory<Favorito, String>("descricaoCoin"));
    		codeApi.setCellValueFactory(new PropertyValueFactory<Favorito, String>("codeApi"));
    		remove.setCellValueFactory(new PropertyValueFactory<Favorito, Button>("button"));
    		
    		tbMoedas.setItems(listaDeFavoritos);
        	
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "ComboBox com probleminhas" + e, "ERRO", JOptionPane.ERROR_MESSAGE);
    	}
    }

}

