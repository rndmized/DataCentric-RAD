package ie.gmit.sw.rad;

import javax.faces.bean.ManagedBean;

@ManagedBean

public class Product {

	private int prodid;
	private String proddesc;

	public Product() {
	}

	public Product(int prodid, String proddesc) {
		super();
		this.prodid = prodid;
		this.proddesc = proddesc;
	}

	public int getProdid() {
		return prodid;
	}

	public void setProdid(int prodid) {
		this.prodid = prodid;
	}

	public String getProddesc() {
		return proddesc;
	}

	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}

}
