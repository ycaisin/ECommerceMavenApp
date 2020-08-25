package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

import com.github.javafaker.Faker;

import dao.CategoryRepository;
import dao.CurrencyRepository;
import dao.MoneyRepository;
import dao.ProductRepository;
import domain.Category;
import domain.CurrencyProvider;
import domain.DataRepository;
import domain.Money;
import domain.Product;
import domain.ProductFactory;

@SuppressWarnings("unused")
public class ECommerceApplication {

public static void main(String[] args) throws IOException, ClassNotFoundException, SQLException {
		
		CurrencyProvider currencyProvider = new CurrencyProvider().getInstance();
		CurrencyRepository cr = new CurrencyRepository();
		MoneyRepository mr = new MoneyRepository();
		CategoryRepository catr= new CategoryRepository();
		//System.out.println(cr.findById(1));
		//cr.addCurrency("EUR", 1);
		//cr.deleteCurrency(3);
		
		//System.out.println(mr.findById(1));
		//mr.addMoney("EUR", 900);
		//mr.deleteMonay(8);
		
		//System.out.println(catr.findById(1));		
		//catr.deleteCategory(3);
		ProductRepository pr = new ProductRepository();
		Product product;
		System.out.println(pr.findById(1));
		
				
		/*ProductFactory productFactory = new ProductFactory();
		DataRepository dataRepository = new DataRepository();

		Faker faker = new Faker();
		Calendar futureDate = Calendar.getInstance();
		futureDate.add(Calendar.DATE, 15);

		Product p = productFactory.getProduct(
				"-- Lady in RED -- picture", 
				new Money("USD", (float)300), 
				2, 
				faker.date().between(
						Calendar.getInstance().getTime(), 
						futureDate.getTime()), 
				"md", 
				new Category("Art"), "/images/product-1.jpg");
		
			dataRepository.save(p);	
			p = dataRepository.load(Product.class);
			System.out.println("Product from file:\n" +p +"\n");
*/

	}


}
