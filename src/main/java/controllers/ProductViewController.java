package controllers;

import java.io.File;
import java.io.IOException;

import domain.DataRepository;
import domain.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import main.ECommerceApplication;

public class ProductViewController {

	@FXML
	AnchorPane showProductPane;
	@FXML
	Label productName ;
	@FXML
	ImageView productImage;
	
	public void showProduct() throws ClassNotFoundException, IOException {
		System.out.println("works");
		//productName.setText("yeee");
		
		
		DataRepository dataRepository = new DataRepository();
		Product p = p = dataRepository.load(Product.class);
		System.out.println(p.getName());
		productName.setText(p.getName());
		
		showProductPane.getChildren().add(productName);
		
		/*
		File file  = new File("src/main/resources/" + imagePath);

		if (file.exists()) 
			return true;
		else 
			return false;
		
		/*
		 try {
		 	ImageIO.read(file);
		} catch (IOException e) {
			return false;
		} 
		return true;*/
	}
}
