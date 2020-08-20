package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIApplication extends Application {

	public static void main(String[] args)  {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox box = FXMLLoader.load(getClass().getResource("/product-full.fxml"));
		
		Scene scene = new Scene(box);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
