/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Asus
 */
public class DBConnexion {
    
       private static Connection connexion;
       
       public DBConnexion(){
           
       
       }
       
       public static Connection getConnexion()
       {
           try {
                DBConnexion.connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev","root",null);
                System.out.println(DBConnexion.connexion.toString());
           } catch (SQLException ex) {
               System.err.println(ex.getMessage());
           }
           return DBConnexion.connexion;
       }
        
        
    
}
