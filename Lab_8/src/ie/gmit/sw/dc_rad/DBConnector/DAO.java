package ie.gmit.sw.dc_rad.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DBClasses.City;
import DBClasses.Superhero;

public class DAO {
	private MySQLConnector msc;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public DAO() throws Exception {
		this.msc = new MySQLConnector();
	}

	/* ======================================================================================================
	 * Public Methods
	 * ====================================================================================================== */
	public ArrayList<Superhero> getSuperheroDetails(String name) throws Exception{
		ArrayList<Superhero> superheroes = new ArrayList<>();
		Connection conn = msc.connect();
		PreparedStatement myStmt = conn.prepareStatement("select * " +
					 "from superhero_table " +
					 "where name = ? ");
		myStmt.setString(1, name);
		ResultSet rs = myStmt.executeQuery();
		while( rs.next() ) {
			name = rs.getString("name");
			String fName = rs.getString("real_first_name");
			String sName = rs.getString("real_surname");
			String dob = rs.getString("dob");
			double powers = rs.getDouble("powers");
			superheroes.add(new Superhero(name, fName, sName, dob, powers));
		}
		msc.disconnect(conn);
		return superheroes;
	}
	
	public void addSuperhero(Superhero superhero){
		Connection conn = msc.connect();
		PreparedStatement myStmt;
		try {
			myStmt = conn.prepareStatement("INSERT INTO superhero_table (`name`,`real_first_name`,`real_surname`,`dob`,`powers`) VALUES ('?','?','?','?','?')");
			myStmt.setString(1, superhero.getName());
			myStmt.setString(2, superhero.getRealFirstName());
			myStmt.setString(3, superhero.getRealSurname());
			myStmt.setString(4, superhero.getDob());
			myStmt.setDouble(5, superhero.getPowers());
			myStmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		msc.disconnect(conn);

		
	}
	
	public ArrayList<City> getCity(String name) throws Exception{
		ArrayList<City> cities = new ArrayList<>();
		Connection conn = msc.connect();
		PreparedStatement myStmt = conn.prepareStatement("select * " +
					 "from city_table " +
					 "where city_name = ? ");
		myStmt.setString(1, name);
		ResultSet rs = myStmt.executeQuery();
		while( rs.next() ) {
			name = rs.getString("city_name");
			String country = rs.getString("country");
			int population = rs.getInt("population");
			cities.add(new City(name, country, population));
		}
		msc.disconnect(conn);
		return cities;
	}
}
