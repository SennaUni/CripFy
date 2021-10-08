package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application{
	
	@Override
	public void start(Stage primaryStage){
		try {
			AnchorPane root = FXMLLoader.load(getClass().getResource("../View/Login.fxml"));
			Scene scene = new Scene(root,815,600);
			primaryStage.setScene(scene);
			primaryStage.setTitle("CripFy");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		launch(args);
	}
}
