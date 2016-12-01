package ie.gmit.sw.rad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.naming.*;
import javax.sql.DataSource;

public class DAO {

	private DataSource mysqlDS;

	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/employeesdb14";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}

	public void deleteProduct(Product p) throws SQLException {
		// add pass through parameter to method for update insert & delete

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("delete from product where PRODID ='" + p.getProdid() + "' and DESCRIP ='" + p.getProddesc() + "'");
		// myStmt.setInt(1, p.getProdid());
		// myStmt.setString(2, p.getProddesc());

		myStmt.executeUpdate();
	}

	public void updateProduct(Product p) throws SQLException {
	 
	 Connection conn = mysqlDS.getConnection(); 
	 PreparedStatement myStmt = conn.prepareStatement("update product set PRODID='" + p.getProdid() + "', DESCRIP='" + p.getProddesc() + "'");
	 
	 myStmt.setInt(1, p.getProdid()); 
	 myStmt.setString(2, p.getProddesc());
	 myStmt.executeUpdate(); 
	 }

	public void addProduct(Product p) throws SQLException {
		// add pass through parameter to method for update insert & delete

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO product values(?, ?)");

		myStmt.setInt(1, p.getProdid());
		myStmt.setString(2, p.getProddesc());

		myStmt.executeUpdate();
	}

	public ArrayList<Product> getProductDetails() throws Exception {

		ArrayList<Product> products = new ArrayList<>();

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from product");

		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) {

			int id = rs.getInt("PRODID");
			String pname = rs.getString("DESCRIP");

			products.add(new Product(id, pname));
		}

		return products;
	}
}