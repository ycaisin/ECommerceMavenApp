package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Currency;

public class CurrencyRepository {

	
	private final String URL = "jdbc:postgresql://localhost/ecommerce?user=postgres&password=123&ssl=false";
	private static Connection conn; 
	
	private static final String find_By_id_query = "SELECT * FROM currencies WHERE id = ?";
	private static final String find_By_code_query = "SELECT * FROM currencies WHERE code  = ?";
	private static final String insert_currency_query = "INSERT INTO currencies (code, rate) VALUES (?,?) ";
	private static final String update_currency_code_query = "UPDATE currencies SET code = ? WHERE id = ?";
	private static final String update_currency_rate_query = "UPDATE currencies SET rate = ? WHERE id = ?";
	private static final String delete_currency_query = "DELETE FROM currencies WHERE id = ?";



	public CurrencyRepository() throws SQLException {
		conn = DriverManager.getConnection(URL);
	}
	
	
	public Currency findById(int id) {
		Currency currency = null;

		try {
			PreparedStatement ps = conn.prepareStatement(find_By_id_query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				currency = new Currency(res.getString("code"),res.getDouble("rate") );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currency;	
	}
	
	public static Currency findByCode(String code) {
		Currency currency = null;

		try {
			PreparedStatement ps = conn.prepareStatement(find_By_code_query);
			ps.setString(1, code);
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				currency = new Currency(res.getString("code"),res.getDouble("rate") );
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return currency;	
	}
	
	public void addCurrency(String code, double rate) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(insert_currency_query);
	        ps.setString(1, code);
	        ps.setDouble(2, rate);
	        ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Can not add currency");
		}
	}
	
	public void updateCurrencyCode(int id, String code) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(update_currency_code_query);
			ps.setString(1, code);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Can not update currency Code!");
		}		
	}
	
	
	public void updateCurrencyRate(int id, double rate) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(update_currency_rate_query);
			ps.setDouble(1, rate);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Can not update currency Code!");
		}		
	}

	public void deleteCurrency (int id) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(delete_currency_query);
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if( res==0 ) System.out.println("No records to delete");
		} catch (SQLException e) {
			System.out.println("Can not delete record!");
		}
	}
	
}
