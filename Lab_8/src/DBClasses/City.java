package DBClasses;

public class City {
	
	private String city;
	private String country;
	private int population;
	
	
	public City(String city, String country, int population) {
		super();
		this.city = city;
		this.country = country;
		this.population = population;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	public int getPopulation() {
		return population;
	}


	public void setPopulation(int population) {
		this.population = population;
	}
	
	

}
