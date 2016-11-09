package DBClasses;


public class Superhero {
	
	private String name;
	private String realFirstName;
	private String realSurname;
	private String dob;
	private double powers;
	
	/* ======================================================================================================
	 * Constructor
	 * ====================================================================================================== */
	public Superhero(String name, String realFirstName, String realSurname, String dob, double powers) {
		super();
		this.name = name;
		this.realFirstName = realFirstName;
		this.realSurname = realSurname;
		this.dob = dob;
		this.powers = powers;
	}
	
	/* ======================================================================================================
	 * Getters/Setters
	 * ====================================================================================================== */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealFirstName() {
		return realFirstName;
	}
	public void setRealFirstName(String realFirstName) {
		this.realFirstName = realFirstName;
	}
	public String getRealSurname() {
		return realSurname;
	}
	public void setRealSurname(String realSurname) {
		this.realSurname = realSurname;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public double getPowers() {
		return powers;
	}
	public void setPowers(double powers) {
		this.powers = powers;
	}

}
