package controllers;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

import javax.imageio.ImageIO;

import domain.CurrencyProvider;
import domain.DataRepository;
import domain.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;

public class ProductViewController implements Serializable{

	@FXML
	AnchorPane showProductPane;
	
	public void showProduct() throws ClassNotFoundException, IOException {

		Label productName = new Label();;
		ImageView productImage;
		
		CurrencyProvider currencyProvider = new CurrencyProvider().getInstance();
		DataRepository dataRepository = new DataRepository();
		
		
		Product p = null;
		p = dataRepository.load(domain.Product.class);
		
		if (p == null) {
			productName.setText("Can not find product");
			productName.setVisible(true);
			showProductPane.getChildren().add(productName);
			return;
		}
		
		productName.setText(p.getName());
		productName.setLayoutX(100.0);
		productName.setLayoutY(0.0);
		productName.setPrefHeight(33.0);
		productName.setPrefWidth(338.0);
		productName.setVisible(true);
		
		File file  = new File("src/main/resources/" + p.getImagePath());
		Image fxImage = new Image(file.toURI().toString());
		productImage = new ImageView(fxImage);
		productImage.setLayoutX(100.0);
		productImage.setLayoutY(50.0);
		productImage.setFitHeight(400);
		productImage.setFitWidth(400);
		productImage.setVisible(true);
		
		showProductPane.getChildren().add(productName);
		showProductPane.getChildren().add(productImage);
	}
}
