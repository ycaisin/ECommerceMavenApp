package domain;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.crypto.Data;

import com.github.javafaker.Faker;
import com.sun.glass.ui.Application;

import exception.ImageNotFoundException;

import javax.imageio.ImageIO;

public class Product implements ProductInterface, Serializable{
	
	private Integer id; 
	private String name;
	private Money price;
	private Integer quantity;
	private Date 	expiration;
	private String 	manufacturer;
	private Category category;
	private String imagePath;
	
	private Product(Integer id,String name, Money price, Integer quantity, Date expiration, String mannufactured,
			Category category, String imagePath) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.expiration = expiration;
		this.manufacturer = mannufactured;
		this.category = category;
		this.imagePath = imagePath ;
	}
	
	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public Integer getId() {
		return id;
	}
	
	public Product() {
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Money getPrice() {
		return price;
	}
	public void setPrice(Money price) {
		this.price = price;
	}
	
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	public String getMannufactured() {
		return manufacturer;
	}
	public void setMannufactured(String mannufactured) {
		this.manufacturer = mannufactured;
	}
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	
	@Override
	public String toString() {
		return "Product [id =  " +id +" name = " + name + ", price = " + price.getCurrency() + ":" + price.getAmount() + ", quantity = " + quantity + ", expiration = " + expiration
				+ ", mannufactured = " + manufacturer + ", category = " + category.getName() + ", product image: " + imagePath.substring(this.imagePath.lastIndexOf("/")+1) + "]";
	}

	@Override
	public Product createProduct(Integer id, String name, Money price, Integer quantity, Date expiration, String mannufactured,
			Category category, String imagePath) {
		if(findProductImage(imagePath)) {
			return new Product(id, name, price, quantity, expiration, mannufactured, category, imagePath);
		}
		return null;
	}


	public boolean findProductImage(String imagePath) {
		//String imageName = this.imagePath.substring(this.imagePath.lastIndexOf("/")+1);
		//String path = "/resources" + this.imagePath.substring(0, this.imagePath.lastIndexOf("/")+1);
		
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
		return true;
		*/
	}
	
	/*
	@Override
	public Product createFakeProduct(Integer id) {
		Faker faker = new Faker();
		
		Calendar futureDate = Calendar.getInstance();
		futureDate.add(Calendar.DATE, 15);
		return new Product(id, faker.commerce().productName(), 
						new Money(CurrencyProvider.getInstance().generateFakeCurrencyCode(), 
						(Float)faker.random().nextInt(0, 2000).floatValue()),
						faker.random().nextInt(0, 50), faker.date().between(Calendar.getInstance().getTime(), futureDate.getTime()) ,
						faker.country().countryCode2(), 
						new Category(faker.commerce().department())  );
		
	}*/

	/*
	@Override
	public List<Product> createManyFakeProducts (int qty, Integer id){
		Faker faker = new Faker();
		List<Product> products = new ArrayList<>();
		
		while(products.size()<qty){
			Calendar futureDate = Calendar.getInstance();
			futureDate.add(Calendar.DATE, 15);
			
			products.add( 
					new Product(++id,faker.commerce().productName(), 
								new Money(CurrencyProvider.getInstance().generateFakeCurrencyCode(), 
								(Float)faker.random().nextInt(0, 2000).floatValue()),
								faker.random().nextInt(0, 50), 
								faker.date().between(Calendar.getInstance().getTime(), futureDate.getTime()) , 
								faker.country().countryCode2(), 
								new Category(faker.commerce().department())  )
					);
		}
		
		return products;
	} */

	

}
