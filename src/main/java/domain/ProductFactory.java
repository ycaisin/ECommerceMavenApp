package domain;

import java.util.Date;
import java.util.List;

public class ProductFactory {
	private static Integer productCount = 0; 
		
	public Product getProduct(String name, Money price, Integer quantity, Date expiration, String mannufactured,
			Category category, String imagePath){
		return new Product().createProduct(
				++productCount, name, price, quantity, expiration, mannufactured, category, imagePath);
	} 
	
	/*public Product getFakeProduct(){
		return new Product().createFakeProduct(++productCount);
	} */
	/*
	public List<Product> getManyFakeProducts(int qty){
		Integer startId = productCount;
		productCount = productCount + qty;
		return new Product().createManyFakeProducts(qty, startId );
	}*/
	
}
