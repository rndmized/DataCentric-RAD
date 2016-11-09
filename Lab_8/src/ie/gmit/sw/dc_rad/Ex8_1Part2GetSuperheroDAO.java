package ie.gmit.sw.dc_rad;



import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import DBClasses.City;
import DBClasses.Superhero;
import ie.gmit.sw.dc_rad.DBConnector.DAO;

// Use sqlinject database

public class Ex8_1Part2GetSuperheroDAO {
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter City");
		String name = keyboard.nextLine();
		keyboard.close();
		
		try {
			DAO dao = new DAO();
			ArrayList<City> cities = dao.getCity(name);
			if (cities.isEmpty()) {
			    System.out.println("No Such Superhero: " + name); 
			} else {
				for (City city : cities) {
					System.out.println("City name: " + city.getCity());
					System.out.println("Country: " + city.getCountry().toUpperCase());
					System.out.println("Population: " + city.getPopulation());
				}
			}
		} catch( SQLException se ) {
            System.out.println(se.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

}
