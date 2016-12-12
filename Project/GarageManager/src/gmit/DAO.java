package gmit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.*;
import javax.sql.DataSource;

public class DAO {
	
	/**************************************************************************************************************************/
	/************************************************** Variables *************************************************************/
	/**************************************************************************************************************************/
	

	private DataSource mysqlDS;

	/**************************************************************************************************************************/
	/************************************************ Constructors ************************************************************/
	/**************************************************************************************************************************/
	
	public DAO() throws Exception {
		Context context = new InitialContext();
		String jndiName = "java:comp/env/jdbc/garage";
		mysqlDS = (DataSource) context.lookup(jndiName);
	}
	
	
	/**************************************************************************************************************************/
	/**************************************************** Delete Functions ****************************************************/
	/**************************************************************************************************************************/

	public void deleteManufacturer(String manu_code) throws SQLException {

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("delete from manufacturer where manu_code = ?;");
		myStmt.setString(1, manu_code);
		myStmt.executeUpdate();
		
	}
	
	public void deleteModel(Model m) throws SQLException {

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("delete from manufacturer where model_code = ?;");
		myStmt.setString(1, m.getModel_code());
		myStmt.executeUpdate();
		
	}
	
	public void deleteVehicle(Vehicle v) throws SQLException {

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("delete from manufacturer where reg = ?;");
		myStmt.setString(1, v.getReg());
		myStmt.executeUpdate();
		
	}
	
	/**************************************************************************************************************************/
	/**************************************************** Update Functions ****************************************************/
	/**************************************************************************************************************************/

	public void updateManufacturer(Manufacturer m) throws SQLException {
	 
		
	 Connection conn = mysqlDS.getConnection(); 
	 PreparedStatement myStmt = conn.prepareStatement("update manufacturer set manu_name = ? , manu_details = ? where manu_code = ? ;");
	 
	 myStmt.setString(1, m.getManu_name()); 
	 myStmt.setString(2, m.getManu_details()); 
	 myStmt.setString(3, m.getManu_code());
	 myStmt.executeUpdate(); 
	 }
	
	/**************************************************************************************************************************/
	/****************************************************** Add Functions *****************************************************/
	/**************************************************************************************************************************/

	public void addManufacturer(Manufacturer m) throws SQLException {
		// add pass through parameter to method for update insert & delete

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO manufacturer values(?, ?, ?)");

		myStmt.setString(1, m.getManu_code());
		myStmt.setString(2, m.getManu_name());
		myStmt.setString(3, m.getManu_details());
		
		myStmt.executeUpdate();
	}
	
	public void addModel(Model m) throws SQLException {

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO model values(?, ?, ?, ?)");
		
		myStmt.setString(1, m.getManu_code());
		myStmt.setString(2, m.getModel_code());
		myStmt.setString(3, m.getModel_name());
		myStmt.setString(4, m.getModel_desc());
		
		myStmt.executeUpdate();
	}
	
	public void addVehicle(Vehicle v) throws SQLException {

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("INSERT INTO vehicle values(?, ?, ?, ?, ?, ?, ?)");

		myStmt.setString(1, v.getReg());
		myStmt.setString(2, v.getManu_code());
		myStmt.setString(3, v.getModel_code());
		myStmt.setFloat(4, v.getMileage());
		myStmt.setDouble(5, v.getPrice());
		myStmt.setString(6, v.getColour());
		myStmt.setString(7, v.getFuel());

		myStmt.executeUpdate();
	}


	/**************************************************************************************************************************/
	/***************************************************** Load Functions *****************************************************/
	/**************************************************************************************************************************/

	public ArrayList<Manufacturer> loadManufacturerDetails() throws Exception {

		ArrayList<Manufacturer> manufacturers = new ArrayList<>();

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from manufacturer");

		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) {

			String manu_code = rs.getString("manu_code");
			String manu_name = rs.getString("manu_name");
			String manu_details = rs.getString("manu_details");

			manufacturers.add(new Manufacturer(manu_code, manu_name, manu_details));
		}

		return manufacturers;
	}
	
	public ArrayList<Model> loadModelDetails() throws Exception {

		ArrayList<Model> models = new ArrayList<>();

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from model");

		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) {
			
			String manu_code = rs.getString("manu_code");
			String model_code = rs.getString("model_code");
			String model_name = rs.getString("model_name");
			String model_desc = rs.getString("model_desc");

			models.add(new Model(manu_code, model_code, model_name, model_desc));
		}

		return models;
	}
	
	public ArrayList<Vehicle> loadVehicleDetails() throws Exception {

		ArrayList<Vehicle> vehicles = new ArrayList<>();

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from vehicle");

		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) {

			String reg = rs.getString("reg");
			String manu_code = rs.getString("manu_code");
			String model_code = rs.getString("model_code");
			Float mileage = rs.getFloat("mileage");
			double price = rs.getDouble("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");

			vehicles.add(new Vehicle(reg, manu_code, model_code, mileage,price,colour,fuel));
		}

		return vehicles;
	}
	
	
	
	/**************************************************************************************************************************/
	/***************************************************** Find Functions *****************************************************/
	/**************************************************************************************************************************/
	
	/* Returns a VehicleDetails matching a given registration number */
	public VehicleDetails loadVehicleDetails(String registration) throws Exception {

		VehicleDetails vehicleDetails = null;

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM vehicle v JOIN model m ON (m.model_code = v.model_code) JOIN manufacturer manu ON (manu.manu_code = m.manu_code) where v.reg = ?");
		myStmt.setString(1, registration);

		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) {

			String reg = rs.getString("reg");
			String manu_code = rs.getString("manu_code");
			String model_code = rs.getString("model_code");
			Float mileage = rs.getFloat("mileage");
			double price = rs.getDouble("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");
			String manu_name = rs.getString("manu_name");
			String manu_details = rs.getString("manu_details");
			String model_name = rs.getString("model_name");
			String model_desc = rs.getString("model_desc");
			
			vehicleDetails = new VehicleDetails(reg, manu_code, manu_name, manu_details, model_code, model_name, model_desc, mileage, price, colour, fuel);
		}

		return vehicleDetails;
	}
	/* Returns an array list of vehicles matching a certain criteria */
	public ArrayList<Vehicle> loadFoundVehicleDetails(VehicleDetails v) throws Exception {
		ArrayList<Vehicle> vehicles = new ArrayList<>();
		int stmtCounter=0;
		boolean valor = false;
		boolean color = false;

		Connection conn = mysqlDS.getConnection();
		String query = "SELECT * FROM vehicle WHERE ";
		
		query += "fuel = ? ";
		stmtCounter++;
		
		if(v.getPrice() > 0){
			if(stmtCounter > 0){
				query += "AND ";
			}
			valor=true;
			stmtCounter++;
			switch(v.getCriteria()){
				case -1:
					query += "price < ? ";
					break;
				case 0:
					query += "price = ? ";
					break;
				case 1:
					query += "price > ? ";
					break;
			}
		}
		
		if(v.getColour() != null && v.getColour() != "" ){
			color = true;
			query += "AND colour = ? ";
			stmtCounter++;
			
		}

		PreparedStatement myStmt = conn.prepareStatement(query);
		myStmt.setString(1, v.getFuel());
		
		if(valor){
			myStmt.setDouble(2, v.getPrice());
		}
		
		if (valor && color){
			myStmt.setString(3,v.getColour());
		}else if (!valor && color){
			myStmt.setString(2,v.getColour());
		}
		

		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) {

			String reg = rs.getString("reg");
			String manu_code = rs.getString("manu_code");
			String model_code = rs.getString("model_code");
			Float mileage = rs.getFloat("mileage");
			double price = rs.getDouble("price");
			String colour = rs.getString("colour");
			String fuel = rs.getString("fuel");
			vehicles.add(new Vehicle(reg, manu_code, model_code, mileage,price,colour,fuel));
			
		}

		return vehicles;
	}
	/* Returns a Manufacturer matching a given manufacturer code */
	public Manufacturer loadManufacturer(String manufacturer_code) throws Exception {

		Manufacturer manufacturer = null;

		Connection conn = mysqlDS.getConnection();
		PreparedStatement myStmt = conn.prepareStatement("select * from manufacturer where manu_code = ?");
		myStmt.setString(1, manufacturer_code);

		ResultSet rs = myStmt.executeQuery();

		while (rs.next()) {

			String manu_code = rs.getString("manu_code");
			String manu_name = rs.getString("manu_name");
			String manu_details = rs.getString("manu_details");

			manufacturer = new Manufacturer(manu_code, manu_name, manu_details);
		}

		return manufacturer;
	}
	
	

}
