package ie.gmit.sw.dc_rad.DBConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import ie.gmit.sw.dc_rad.Superhero;

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
}
