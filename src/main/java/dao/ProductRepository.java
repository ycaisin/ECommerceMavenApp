package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProductRepository {
	private final String URL = "jdbc:postgresql://localhost/ecommerce?user=postgres&password=123&ssl=false";
	private Connection conn; // = DriverManager.getConnection(url);


	public ProductRepository() throws SQLException {
		conn = DriverManager.getConnection(URL);
	}
}
