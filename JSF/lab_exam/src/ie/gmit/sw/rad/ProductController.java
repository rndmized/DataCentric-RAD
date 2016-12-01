package ie.gmit.sw.rad;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
//import javax.faces.event.ComponentSystemEvent;

@ManagedBean
public class ProductController {

	private ArrayList<Product> products;
	private DAO dao;

	public ProductController() {
		try {
			dao = new DAO();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void loadProducts() throws Exception {
		products = dao.getProductDetails();
	}

	public String addProduct(Product p) throws Exception {
		try {
			dao.addProduct(p);
			return "helloOutput";
		} catch (Exception e) {
			return e.toString();
		}

	}

	public String updateProduct(Product p) throws Exception {
		try {
			dao.updateProduct(p);
			return "helloOutput";
		} catch (Exception e) {
			return e.toString();
		}
	}

	public String deleteProduct(Product p) throws Exception {

		try {
			dao.deleteProduct(p);
			return "helloOutput";
		} catch (Exception e) {
			return e.toString();
		}
	}
}