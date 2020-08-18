package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Cart implements ProductRepository{
	
	private static Cart instance;
	
	private List<Product> products = new ArrayList<Product>();

	private static Money total = new Money("MDL",(float)0);
	
	
	public Cart(){
	};
	
	public static Cart getInstance(){
		if(instance == null)
			synchronized (Cart.class) {
				if(instance == null)
					instance = new Cart();
			}
		return instance;
	}
	
	@Override
	public void add(Product product) {
		if(product!=null)
			products.add(product);
		
	}

	@Override
	public void delete(Integer productId) {
		Product product = findById(productId);
		products.remove(product);
		
	}

	@Override
	public void updateQuantity(Integer productId, Integer newQty) {
		Product product = findById(productId);
		product.setQuantity(newQty);
		
	}

	@Override
	public Product findById(Integer productId) {
		for (Product p : products) {
			if(p.getId().equals(productId)){
				return p;
			}
		}
		
		return null;
	}

	@Override
	public List<Product> findAll() {
		return products;
	}

	@Override
	public List<Product> findByName(String productName) {
		List<Product> foundProducts = new ArrayList<Product>();
		products.forEach(p -> {if(p.getName().equals(productName))
				foundProducts.add(p);});
		
		return foundProducts;
	}

	@Override
	public List<Product> findByManufacturer(String productManufacturer) {
		List<Product> foundProducts = new ArrayList<Product>();
		for (Product p : products) {
			if(p.getMannufactured().equals(productManufacturer))
				foundProducts.add(p);
		}
		return foundProducts;
	}

	@Override
	public List<Product> findByCategory(String productCategory) {
		List<Product> foundProducts = new ArrayList<Product>();
		for (Product p : products) {
			if(p.getCategory().getName().equals(productCategory))
				foundProducts.add(p);
		}
		return foundProducts;
	}

	@Override
	public List<Product> findByExpirationDateFrom(Date fromDate) {
		List<Product> foundProducts = new ArrayList<Product>();
		for (Product p : products) {
			if(p.getExpiration().after(fromDate))
				foundProducts.add(p);
		}
		return foundProducts;
		
		
	}

	@Override
	public List<Product> findByExpirationDateTo(Date toDate) {
		List<Product> foundProducts = new ArrayList<Product>();
		for (Product p : products) {
			if(p.getExpiration().before(toDate))
				foundProducts.add(p);
		}
		return foundProducts;	
	}

	@Override
	public List<Product> findByExpirationDateBetween(Date fromDate, Date toDate) {
		List<Product> foundProducts = new ArrayList<Product>();
		for (Product p : products) {
			if(p.getExpiration().before(toDate)&&p.getExpiration().after(fromDate))
				foundProducts.add(p);
		}
		return foundProducts;	
	}

	
	///////// 
	
	public Money getTotal(){
		return total;
	}; 
	
	public void calculateTotal(String code){
		Float sum = (float) 0;
		Float exchangedValue = (float) 0;
		for (Product p : products) {
				if(code.equals(p.getPrice().getCurrency().getCode())){
					sum = sum +p.getPrice().getAmount() * p.getQuantity();
				}else{
					exchangedValue = (float) p.getPrice().toCurrency(code).getRate();
					sum = sum + exchangedValue*p.getQuantity();
				}
		}
		total.setAmount(sum); 
	};
	

}
