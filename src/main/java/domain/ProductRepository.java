package domain;

import java.util.Date;
import java.util.List;

public interface ProductRepository {
	public void add(Product product);
	public void delete(Integer productId);
	public void updateQuantity(Integer productId, Integer newQty);
	
	public Product findById(Integer productId);
	public List<Product> findAll();
	public List<Product> findByName(String productName);
	public List<Product> findByManufacturer(String productManufacturer);
	public List<Product> findByCategory(String productCategory);
	
	
	public List<Product> findByExpirationDateFrom(Date fromDate);
	public List<Product> findByExpirationDateTo(Date toDate);
	public List<Product> findByExpirationDateBetween(Date fromDate, Date toDate);
}
