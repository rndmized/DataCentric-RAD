package ie.gmit.sw.dc_rad;



import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ie.gmit.sw.dc_rad.DBConnector.DAO;

import java.util.ArrayList;
import java.util.Scanner;

// Use sqlinject database

public class Ex8_1Part1GetSuperheroDAO {
	public static void main(String[] args) {

		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter Superhero");
		String name = keyboard.nextLine();
		keyboard.close();
		try {
			DAO dao = new DAO();
			ArrayList<Superhero> superheroes = dao.getSuperheroDetails(name);
			if (superheroes.isEmpty()) {
			    System.out.println("No Such Superhero: " + name); 
			} else {
				for (Superhero superhero : superheroes) {
					System.out.println("Superhero: " + superhero.getName());
					System.out.println("Real Name: " + superhero.getRealSurname().toUpperCase() + "," + superhero.getRealFirstName());
					System.out.println("DOB:       " + superhero.getDob());
					System.out.println("Powers:    " + superhero.getPowers());
				}
			}
		} catch( SQLException se ) {
            System.out.println(se.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

}
