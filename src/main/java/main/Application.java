package main;

import java.io.IOException;
import java.util.Calendar;

import com.github.javafaker.Faker;

import domain.Category;
import domain.CurrencyProvider;
import domain.DataRepository;
import domain.Money;
import domain.Product;
import domain.ProductFactory;

public class Application {

public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		CurrencyProvider currencyProvider = new CurrencyProvider().getInstance();
		ProductFactory productFactory = new ProductFactory();
		DataRepository dataRepository = new DataRepository();

		Faker faker = new Faker();
		Calendar futureDate = Calendar.getInstance();
		futureDate.add(Calendar.DATE, 15);

		Product p = productFactory.getProduct(
				"Hat", 
				new Money("USD", (float)300), 
				2, 
				faker.date().between(
						Calendar.getInstance().getTime(), 
						futureDate.getTime()), 
				"md", 
				new Category("1"), "/images/product-1.jpg");
		
		//if(p!=null) {
	
			dataRepository.save(p);	
			p = dataRepository.load(Product.class);
			System.out.println("Product from file:\n" +p +"\n");
		//}
		//else 
			//System.out.println("Can not create product");
		
		//getImagesFromDirectory();

	}


}
