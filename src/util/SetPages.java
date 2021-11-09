package util;

import java.io.IOException;

import main.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SetPages {

	public static void HomePage(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/Principal.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Minha DashBoard");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public static void HomePage(MouseEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/Principal.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Minha DashBoard");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public static void FavPage(MouseEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/Favoritos.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Meus Favoritos");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public static void CarteiraPage(MouseEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/MinhaCarteira.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Minha Carteira");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public static void PerfilPage(MouseEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/MinhaConta.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Minha Conta");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public static void CompraPage(MouseEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/SimularCompra.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Simular Compra");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public static void VendaPage(MouseEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/SimularVenda.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Simular Venda");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
	
	public static void LoginPage(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/Login.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("Seja bem vindo(a) a CripFy");
		stage.setScene(new Scene(root,815,600));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
}
