/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Service;
import Utils.DBConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class GererServices {
    private static Connection cnx=DBConnexion.getConnexion();
    public  ArrayList<String> getAllServices()
    {
        PreparedStatement select;
        ResultSet resultat;
        
        ArrayList<String> messages=new ArrayList<>();
        try {
            select=cnx.prepareStatement("select * from service");
            resultat=select.executeQuery();
            while(resultat.next())
            {
                
                //m.setTo_nom(resultat.getString("nom"));
                //m.setTo_id(to_id);
                messages.add(resultat.getString("nomService"));
            }
            
            
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return messages;
    }
    
}
