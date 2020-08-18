package domain;

import java.util.Date;

public interface ProductInterface {
	
	public ProductInterface createProduct(Integer id, String name, Money price, Integer quantity, Date expiration, String mannufactured,
			Category category, String imagePath);
	
	//public Product createFakeProduct(Integer id);
	
	//public List<Product> createManyFakeProducts(int qty,Integer id);
}
