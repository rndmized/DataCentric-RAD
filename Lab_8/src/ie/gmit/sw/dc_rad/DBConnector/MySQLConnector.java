package ie.gmit.sw.dc_rad.DBConnector;

import java.sql.Connection;
import java.sql.DriverManager;


/**
 *
 * @author RnDMizeD
 * 
 * This class establishes and closes the connection with the database using the values stored in the Credentials class.
 */
public class MySQLConnector {
    
    public Connection connect(){
        try {
            Class.forName(Credentials.driver).newInstance();
            Connection conn = DriverManager.getConnection(Credentials.url+Credentials.dbName,Credentials.userName,Credentials.password);
            return conn;
        } catch (Exception e) {
                System.out.println("ERROR: Cannot communicate with database");
                e.printStackTrace();
        }
        return null;
    }
    
    public void disconnect(Connection conn){
        try {
            conn.close();
        }catch (Exception e){}    
    }
}
