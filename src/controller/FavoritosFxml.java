package controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entity.Moeda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import util.util;
import javafx.scene.control.Label;

public class FavoritosFxml {

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
    private TableView<?> tbMoedas;

    public void initialize() {
    	FavoritosController fav = new FavoritosController();
    	
    	try {
    		
    		ObservableList<Moeda> listaDeMoedas = FXCollections.observableList(fav.selectAll());
    		
    		System.out.println(listaDeMoedas);
    				
    		cBoxMoeda.setItems(listaDeMoedas);
        	
    	} catch (Exception e) {
    		JOptionPane.showMessageDialog(null, "ComboBox com probleminhas", "ERRO", JOptionPane.ERROR_MESSAGE);
    	}
	}
    
    @FXML
    void adicionarMoeda(ActionEvent event) {

    }

}

