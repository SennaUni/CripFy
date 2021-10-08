package util;

import java.io.IOException;

import main.Principal;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SetPages {

	public static void HomePage(ActionEvent event) throws IOException{
		
		Parent root = FXMLLoader.load(Principal.class.getResource("../View/Principal.fxml"));
	 	Stage stage = new Stage();
		stage.setTitle("My New Stage Title");
		stage.setScene(new Scene(root, 900, 700));
		stage.show();
		((Node)(event.getSource())).getScene().getWindow().hide();
	}
}
