package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Currency;
import domain.CurrencyProvider;
import domain.Money;

public class MoneyRepository {
	
	private final String URL = "jdbc:postgresql://localhost/ecommerce?user=postgres&password=123&ssl=false";
	private static Connection conn; 
	
	private static final String find_By_id_query = "SELECT * FROM money INNER JOIN currencies ON money.currency_id = currencies.id WHERE money.id = ? ";
	private static final String insert_money_query = "INSERT INTO money (currency_id, amount) VALUES (?,?) ";
	private static final String update_amount_query = "UPDATE money SET amount = ? WHERE id = ?";
	private static final String delete_money_query = "DELETE FROM money WHERE id = ?";

	public MoneyRepository() throws SQLException {
		conn = DriverManager.getConnection(URL);
	}
	
	
	public Money findById(int id) {
		Money money = null;

		try {
			PreparedStatement ps = conn.prepareStatement(find_By_id_query);
			ps.setInt(1, id);
			ResultSet res = ps.executeQuery();
			
			if (res.next()) {
				Currency currency = new Currency(res.getString("code"),res.getDouble("rate") );
				money = new Money(currency.getCode(), res.getFloat("amount"));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return money;	
	}
	
	public void addMoney(String code, double amount) {
		PreparedStatement ps;
		try {
			ps = conn.prepareStatement(insert_money_query);
			Currency c = CurrencyRepository.findByCode(code);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("Select id, code from currencies where code ='" + code +"'");

			if(rs.next()) {
		        ps.setInt(1, rs.getInt("id"));
		        ps.setDouble(2, amount);
		        ps.executeUpdate();
			}

		} catch (SQLException e) {
			System.out.println("Can not add money");
		}
	}
	
	public void updateAmount(int id, Float amount) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(update_amount_query);
			ps.setFloat(1, amount);
			ps.setInt(2, id);
			ps.executeUpdate();
		} catch (SQLException e1) {
			System.out.println("Can not update amount!");
		}		
	}
	
	public void deleteMonay (int id) {
		try {
			PreparedStatement ps;
			ps = conn.prepareStatement(delete_money_query);
			ps.setInt(1, id);
			int res = ps.executeUpdate();
			if( res==0 ) System.out.println("No records to delete");
		} catch (SQLException e) {
			System.out.println("Can not delete record!");
		}
	}

	
	
}
