package it.alfasoft.propertiesmanager;

import java.io.InputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class PropertiesManager {
    private static Properties prop;
    public static Properties getProperties() throws FileNotFoundException{
        //Singleton
        if(prop==null){
            prop = new Properties();
            
            String urlKey = "dburl";
            String userKey = "dbuser";
            String passwordKey = "dbpassword";

            String dbUrl = System.getenv(urlKey);
            String dbUser = System.getenv(userKey);
            String dbPassword = System.getenv(passwordKey);

            if(dbUrl != null && dbUser != null && dbPassword != null){
                prop.setProperty(urlKey, dbUrl);
                prop.setProperty(userKey, dbUser);
                prop.setProperty(passwordKey, dbPassword);
            }

            else {
                    //try (FileInputStream input = new FileInputStream("C:\\Users\\alebr\\Documents\\FoodDelivery\\FoodDelivery\\PropertiesManager\\src\\main\\resources\\config.properties")) {
                    try ( InputStream input = PropertiesManager.class.getClassLoader().getResourceAsStream("config.properties") ) {
                        prop.load(input);
                    }catch (Exception e) { System.out.println("File non trovato"); }
            }

        }
        return prop;
    }
}