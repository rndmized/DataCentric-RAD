package gmit;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class GarageController {
	
	private DAO dao;
	private ArrayList<Manufacturer> manufacturers;
	private ArrayList<Model> models;
	private ArrayList<Vehicle> vehicles;
	
	public GarageController(){
		try {
			dao = new DAO();
		} catch (Exception e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}
	
	
	/**************************************************************************************************************************/
	/******************************************* Load Instance Functions ******************************************************/
	/**************************************************************************************************************************/
	
	
	
	public String loadManufacturerUpdate(String manu_code){
		
		Manufacturer m;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		try {
			m = dao.loadManufacturer(manu_code);
			requestMap.put("manufacturer", m);
			return "updateManufacturer.xhtml";
		} catch (Exception e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String loadVehicleDetails(String reg){
		
		VehicleDetails v;
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> requestMap = externalContext.getRequestMap();
		try {
			v = dao.loadVehicleDetails(reg);
			requestMap.put("selectedVehicle", v);
		} catch (Exception e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "vehicle_details.xhtml";
		
	}
	
	
	/**************************************************************************************************************************/
	/*********************************************** Delete Functions *********************************************************/
	/**************************************************************************************************************************/
	
	
	public String deleteManufacturer(String manu_code){
		try {
			dao.deleteManufacturer(manu_code);
		} catch (SQLException e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "manufacturer.xhtml";
		
	}
	
	public String deleteModel(Model m){
		try {
			dao.deleteModel(m);
		} catch (SQLException e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "model.xhtml";
		
	}
	
	public String deleteVehicle(Vehicle v){
		try {
			dao.deleteVehicle(v);
		} catch (SQLException e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return "vehicle.xhtml";
		
	}

	
	/**************************************************************************************************************************/
	/************************************************** Update Functions ******************************************************/
	/**************************************************************************************************************************/
	
	public String updateManufacturer(Manufacturer m){
		try {
			dao.updateManufacturer(m);
			return "manufacturer.xhtml";
		} catch (SQLException e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		
		return null;
	}
	
	
	/**************************************************************************************************************************/
	/***************************************************** Add Functions ******************************************************/
	/**************************************************************************************************************************/
	
	public String addManufacturer(Manufacturer m){
		try {
			dao.addManufacturer(m);
			return "manufacturer.xhtml";
		} catch (SQLException e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String addModel(Model m){
		try {
			dao.addModel(m);
			return "model.xhtml";
		} catch (SQLException e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	public String addVehicle(Vehicle v){
		try {
			dao.addVehicle(v);
			return "vehicle.xhtml";
		} catch (SQLException e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}
	
	/**************************************************************************************************************************/
	/***************************************************** Find Functions *****************************************************/
	/**************************************************************************************************************************/
	

	public String searchVehicles(VehicleDetails v) {
		try {
			vehicles = null;
			vehicles = dao.loadFoundVehicleDetails(v);
			return "find_vehicle.xhtml";
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}


	/**************************************************************************************************************************/
	/***************************************************** Load Functions *****************************************************/
	/**************************************************************************************************************************/
	
	public void loadManufacturers() {
		try {
			manufacturers = dao.loadManufacturerDetails();
		} catch (Exception e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void loadModels() {
		try {
			models = dao.loadModelDetails();
		} catch (Exception e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void loadVehicles() {
		try {
			vehicles = dao.loadVehicleDetails();
		} catch (Exception e) {
			FacesMessage message = 	new FacesMessage("Error: "+ e.getMessage());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	
	/**************************************************************************************************************************/
	/****************************************************** ** Getters ** *****************************************************/
	/**************************************************************************************************************************/
	
	public DAO getDao() {
		return dao;
	}
	
	public ArrayList<Manufacturer> getManufacturers() {
		return manufacturers;
	}

	public ArrayList<Model> getModels() {
		return models;
	}

	public ArrayList<Vehicle> getVehicles() {
		return vehicles;
	}

}
