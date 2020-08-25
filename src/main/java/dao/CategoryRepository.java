package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Category;
import domain.Currency;

public class CategoryRepository {

	
	private final String URL = "jdbc:postgresql://localhost/ecommerce?user=postgres&password=123&ssl=false";
	private static Connection conn; 
	
	private static final String find_By_id_query = "SELECT * FROM categories WHERE id = ?";
	private static final String find_By_name_query = "SELECT * FROM categories WHERE name  = ?";
	private static final String insert_category_query = "INSERT INTO categories (name) VALUES (?) ";
	private static final String update_category_name_query = "UPDATE categories SET name = ? WHERE id = ?";
	private static final String delete_category_query = "DELETE FROM categories WHERE id = ?";



	public CategoryRepository() throws SQLException {
		conn = DriverManager.getConnection(URL);
	}
	
	
	public Category findById(int id) {
		Category category = null;

		try {
			PreparedStatement ps = conn.prepareStatement(find_By_id_query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				category = new Category(res.getString("name") );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;	
	}
	
	public Category findByName(String name) {
		Category category = null;

		try {
			PreparedStatement ps = conn.prepareStatement(find_By_name_query);
			ps.setString(1, name);
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				category = new Category(res.getString("name") );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;	
	}
	
	public void addCategory(String name) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(insert_category_query);
	        ps.setString(1, name);
	        ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Can not add category");
		}
	}
	
	public void updateCategoryName(int id, String name) {
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

	public void deleteCategory (int id) {
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
