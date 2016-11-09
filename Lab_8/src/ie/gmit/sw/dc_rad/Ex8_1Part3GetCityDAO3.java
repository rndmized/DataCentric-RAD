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

import DBClasses.Superhero;

// Use sqlinject database

public class Ex8_1Part3GetCityDAO3 {
	public static void main(String[] args) {
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter Superhero Name");
		String name = keyboard.nextLine();
		System.out.println("Enter Superhero Real First Name");
		String real_first_name = keyboard.nextLine();
		System.out.println("Enter Superhero Real Surname");
		String real_surname = keyboard.nextLine();
		System.out.println("Enter Superhero dob 'Stringyfied'");
		String dob = keyboard.nextLine();
		System.out.println("Enter Superhero Powers");
		double powers = keyboard.nextDouble();
		
		keyboard.close();
		
		try {
			DAO dao = new DAO();
			dao.addSuperhero(new Superhero(name, real_first_name, real_surname, dob, powers));;
		} catch( SQLException se ) {
            System.out.println(se.getMessage());
        } catch (Exception e) {
        	e.printStackTrace();
        }
	}

}
