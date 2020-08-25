package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import domain.Category;
import domain.Money;
import domain.Product;

public class ProductRepository {
	private final String URL = "jdbc:postgresql://localhost/ecommerce?user=postgres&password=123&ssl=false";
	private static Connection conn; // = DriverManager.getConnection(url);

	private static final String find_By_id_query = "SELECT * FROM products WHERE id = ?";
	private static final String insert_product_query = "INSERT INTO products "
										+ "(id, name, money_id, quantity, expiration, manufacturer, category_id,  imagePath)"
										+ " VALUES (?,?,?,?,?,?,?,? ) ";
	private static final String update_category_name_query = "UPDATE categories SET name = ? WHERE id = ?";
	private static final String delete_category_query = "DELETE FROM categories WHERE id = ?";

	public ProductRepository() throws SQLException {
		conn = DriverManager.getConnection(URL);
	}
	
	public Product findById(int id) throws SQLException {
		
		Product product = null;
		MoneyRepository mr = new MoneyRepository();
		CategoryRepository cr = new CategoryRepository();

		try {
			PreparedStatement ps = conn.prepareStatement(find_By_id_query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				
				product = new Product(res.getInt("id"), res.getString("name"), mr.findById(res.getInt("money_id")), 
						res.getInt("quantity"), res.getDate("expiration"), res.getString("manufacturer"),
						cr.findById(res.getInt("category_id")), res.getString("imagePath"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return product;	
	}
	
	public static void addProduct(String name , Money money, int quantity, Date expiration, String manufacturer, Category category, String imagePath) throws SQLException {
		
		CurrencyRepository cr = new CurrencyRepository(); 
		MoneyRepository mr = new MoneyRepository();
		int categoryId = -1;
		int moneyId = -1;
		try {
			Statement st = conn.createStatement(); 
			mr.addMoney(cr.findByCode(money.getCurrency().getCode()).getCode(), money.getAmount());
			//"SELECT * FROM money INNER JOIN currencies ON money.currency_id = currencies.id WHERE currency.code = " +   money.getCurrency().getCode() ); 
			
			PreparedStatement ps = conn.prepareStatement(insert_product_query); 
	        ps.setString(1, name); 


	        // ps.setInt(2, mr.findById("money_id"));
	        
	        ps.setInt(3, quantity);
	        ps.setDate(4, (java.sql.Date) expiration);
	        ps.setString(5, manufacturer);
	        
	        ResultSet rs = st.executeQuery("Select * from categories WHERE category = '" + category.getName() + "';");
			if(rs.next()) {
				categoryId = rs.getInt("id");
			}

	        ps.setInt(6, categoryId);
	        ps.setString(7, imagePath);
	        ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Can not add category");
		}
	}
	
	public static void updateCategoryName(int id, String name) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(update_category_name_query);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Can not update category name!");
		}		
	}

	public static void deleteCategory (int id) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(delete_category_query);
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if( res==0 ) System.out.println("No records to delete");
		} catch (SQLException e) {
			System.out.println("Can not delete record!");
		}
	}

	
	
	
}
